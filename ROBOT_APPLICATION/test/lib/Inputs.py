from robot.api.deco import keyword, library
from selenium.webdriver import ActionChains
from selenium.webdriver.common.keys import Keys

from Common import Common


import os
@library(scope='TEST', version='1.0')
class Inputs(Common):
    def __init__(self):
        pass

    @keyword
    def type_text_in_label(self, text=None, label=None, number="1"):
        xpath = self._generate_input_inlabel_xpath(label, number)
        self._get_element(xpath).send_keys(text)

    @keyword
    def type_password_with_no_label(self, text, number="1"):
        xpath = self._generate_input_xpath_with_no_label_for_type_password(number)
        self._get_element(xpath).send_keys(text)



    @keyword
    def type_text_with_no_label(self, text, number="1"):
        xpath=self._generate_input_xpath_with_no_label_for_type_text(number)
        self._get_element(xpath).send_keys(text)


    @keyword
    def type_text_for_search(self, text,filtername,number="1"):
        xpath = self._generate_input_xpath_with_no_label_for_place_holder(filtername,number)
        self._get_element(xpath).send_keys(text)

    @keyword
    def type_text_with_name(self,text, name, number="1"):
        xpath = self._generate_input_xpath_with_name(name, number)
        self._get_element(xpath).send_keys(text)

    @keyword
    def file_upload(self, path_to_file, label=None, number="1"):
        if not os.path.isfile(path_to_file):
            raise ValueError(f"Le fichier spécifié n'existe pas : {path_to_file}")
        xpath=self._generate_file_input_xpath(label,number)
        self._get_element(xpath).send_keys(path_to_file)

    @keyword
    def folder_upload(self, path_to_file):
        if not os.path.isfile(path_to_file):
            raise ValueError(f"Le dossierr spécifié n'existe pas : {path_to_file}")
        xpath = self._generate_folder_input_xpath()
        self._get_element(xpath).send_keys(path_to_file)

    @keyword
    def clear_with_backspace(self, n):
        actions = ActionChains(self._get_driver())
        for i in range(int(n)):
            actions.send_keys(Keys.BACKSPACE)

        actions.perform()

    def _generate_file_input_xpath(self, label, number):
        if label is None :
            return '''(//input[@type='file'])[{number}]'''.format(number=number)
        xpath = '''(//*[.//*[normalize-space(text())="{label}"]]//input[@type='file'])[{number}]'''
        xpath = xpath.format(label=label,number=number)
        return xpath

    def _generate_folder_input_xpath(self):
        xpath= '''(//input[@type='file' and @webkitdirectory='' ])'''
        xpath = xpath.format()
        return xpath

    def _generate_input_inlabel_xpath(self, label, number):
        if label is None :
            return '''(//input)[{number}]'''.format(number=number)
        xpath = '''(//*[./label[normalize-space(text())="{label}"]]//input)[{number}]'''
        xpath = xpath.format(label=label,number=number)
        return xpath



    def _generate_input_xpath_with_no_label_for_type_text(self, number):
        xpath = '''(//input[@type="text"][not(@role)])[{number}]'''
        xpath = xpath.format(number=number)
        return xpath

    def _generate_input_xpath_with_no_label_for_type_password(self, number):
        xpath = '''(//input[@type="password"])[{number}]'''
        xpath = xpath.format(number=number)
        return xpath




    def _generate_input_xpath_with_no_label_for_type_password(self, number):
        xpath = '''(//input[@type="password"])[{number}]'''
        xpath = xpath.format(number=number)
        return xpath

    def _generate_input_xpath_with_name(self,name,number):
        xpath = '''(//input[@name="{name}"])[{number}]'''
        xpath = xpath.format(name=name,number=number)
        return xpath

    def _generate_input_xpath_with_no_label_for_place_holder(self, filtername, number):
        if filtername == "Global Search":
            xpath = '//input[@placeholder=" Global Search"][{number}]'
            xpath = xpath.format(number=number)
        else:
            xpath = '//input[@placeholder="{filtername}"][{number}]'
            xpath = xpath.format(filtername=filtername,number=number)
        return xpath











