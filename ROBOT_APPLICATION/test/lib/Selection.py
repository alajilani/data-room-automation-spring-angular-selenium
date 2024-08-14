from robot.api.deco import keyword, library
from enum import Enum
from robot.libraries.BuiltIn import BuiltIn
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.webdriver import WebDriver

class GlobalSelection(Enum):
    NoSelection=0
    Window=1
    MainMenu=2
    SideMenu=3

@library(scope='TEST', version='1.0')
class Selection:
    _global_selection= GlobalSelection.NoSelection

    _window_name= ""

    _treetable_selected=False
    _table_selected=False
    _table2_selected=False
    _table_row= ""
    _table_column= ""
    _table_number=1
    _table_selected_empty=False
    _filter_selected=False
    _toolbar_selected = False
    _tr_selected = False
    _div_class2 = ""
    _fieldset_selected=False
    _fieldset_with_name_selected=False
    _fieldset_name= ""
    _div_selected = False
    _div_class = ""
    _table_selected_checkbox=False
    _name_treetable =""
    _treetable_with_name_selected=False
    _table_row_with_name=""
    _table_cell_with_name=""
    _tutorial_selected = False
    _tutorial_class = ""
    _table_selected1=False
    _table_row_with_name1 =""
    _table_column1 = ""


    def __init__(self):
        pass
    @keyword
    def select_window(self,window_name=None):
        self._global_selection=GlobalSelection.Window
        self._window_name=window_name

    @keyword
    def clear_selection(self):
        self._global_selection = GlobalSelection.NoSelection
        self.clear_table_selection()
        self._treetable_selected = False
        self._table_selected = False
        self._table2_selected = False
        self._table_selected_empty = False
        self._filter_selected = False
        self._toolbar_selected = False
        self._tr_selected = False
        self._fieldset_selected = False
        self._fieldset_with_name_selected = False
        self._div_selected = False
        self._table_selected_checkbox = False
        self._treetable_with_name_selected = False
        self._tutorial_selected = False
        self._tutorial_class = ""

    @keyword
    def select_p_table(self,number='1'):
        self._table_selected=True
        self._table_number = int(number)

    @keyword
    def select_fieldset_with_name(self,name):
        self._fieldset_selected=True
        self._fieldset_name=name

    @keyword
    def clear_seletion_fieldset(self):
        self._fieldset_selected = False





    @keyword
    def select_table_row_with_name(self,name):
        self._table_selected1 = True
        self._table_row_with_name1 =name
        self._table_column1 = ""




    @keyword
    def select_p_table_row_with_name(self,name):
        self._table_selected=True
        self._table_row_with_name=name
        self._table_column= ""



    @keyword
    def clear_table_selection(self):
        self._table_selected_checkbox=False
        self._treetable_selected=False
        self._treetable_with_name_selected=False
        self._table_selected=False
        self._table_selected_last_element=False
        self._table_selected_empty=False
        self._table2_selected = False
        self._table_row= ""
        self._table_column= ""
        self._table_number=1
        self._table_selected1 = False
        self._table_column1 = ""




    def _create_xpath(self):
        xpath=""
        if self._tutorial_selected:
            xpath += '''//div[div/h1[@class='introjs-tooltip-title' and text()='{tutorial_class}']]
            '''.format(tutorial_class=self._tutorial_class)
        if self._div_selected:
            xpath += '''//*[contains(@class, "{div_class}")]
            '''.format(div_class=self._div_class)
        if self._tr_selected:
            xpath += '''//p-treetable//tr[td[normalize-space(.)="{div_class2}"]]'''.format(div_class2=self._div_class2)
        if(self._global_selection==GlobalSelection.Window):
            if self._window_name is None:
                xpath = '''(//div[contains(@class, "ui-dialog-resizable")])'''
            else:
                xpath = '''//div[contains(@class, 'p-dialog-resizable') and .//*[contains(text(), '{window_name}')]]
'''.format(window_name=self._window_name)
        elif (self._global_selection == GlobalSelection.MainMenu):
            xpath = '''//header'''

        elif (self._global_selection == GlobalSelection.SideMenu):
            xpath = '''//aside'''
        elif(self._global_selection == GlobalSelection.NoSelection):
            pass
        if(self._fieldset_selected):
            xpath += '''//fieldset[./legend[normalize-space(.//text())="{field_name}"]]
            '''.format(field_name=self._fieldset_name)
        if(self._fieldset_with_name_selected):
            xpath += '''//fieldset//*[normalize-space(text())="{field_name}"]
            '''.format(field_name=self._fieldset_name)
        if(self._filter_selected):
            xpath += '''//*[contains(@class,"filter-section")]'''
        if(self._toolbar_selected):
            xpath += '''//p-toolbar'''
        if(self._table_selected):
            xpath = '''({original}//p-table)[{number}]'''.format(original=xpath, number=self._table_number)
            if (self._table_row != ""):
                xpath = '''({original}//tr)[{row}]'''.format(original=xpath, row=self._table_row)
            if (self._table_row_with_name != ""):
                xpath += '''//tr[td[normalize-space(.)="{name}"]]'''.format(name=self._table_row_with_name)
            if (self._table_cell_with_name != ""):
                                    xpath += '''//tr//*[normalize-space(.)="{name}"]'''.format(name=self._table_cell_with_name)
            if (self._table_column != ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column)

        if (self._table_selected1):
            xpath = '''({original}//table)'''.format(original=xpath)
            if (self._table_row_with_name1 != ""):
                xpath += '''//tr[td[normalize-space(.)="{name}"]]'''.format(name=self._table_row_with_name1)
            if (self._table_column1!= ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column1)


        elif(self._table_selected_checkbox):
            xpath = '''({original}//p-table//tr)'''.format(original=xpath, number=self._table_number)
            if (self._table_column != ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column)
        elif (self._treetable_selected):
            xpath = '''({original}//p-treetable)[{number}]'''.format(original=xpath, number=self._table_number)
            if (self._table_row != ""):
                xpath = '''({original}//tr)[{row}]'''.format(original=xpath, row=self._table_row)
            if (self._table_column != ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column)
        elif (self._treetable_with_name_selected):
            xpath = '''({original}//p-treetable//tr[th[normalize-space(.)="{name_treetable}"]]/ancestor::p-treetable)'''.format(original=xpath, name_treetable=self._name_treetable)
        elif (self._table2_selected):
            xpath = '''({original}//table)[{number}]'''.format(original=xpath, number=self._table_number)
            if (self._table_row != ""):
                xpath = '''({original}//tr)[{row}]'''.format(original=xpath, row=self._table_row)
            if (self._table_column != ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column)
        elif (self._table_selected_empty):
            xpath = '''({original}//p-table)'''.format(original=xpath)
            if (self._table_row != ""):
                xpath = '''({original}//tr)[{row}]'''.format(original=xpath, row=self._table_row)
            if (self._table_column != ""):
                xpath += '''/*[{col}]'''.format(col=self._table_column)
        return xpath


