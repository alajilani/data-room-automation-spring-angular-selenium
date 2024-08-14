
from robot.api.deco import keyword, library
from robot.libraries.BuiltIn import BuiltIn
from Common import Common
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import time
from selenium.common.exceptions import TimeoutException
@library(scope='TEST', version='1.0')
class Verification(Common):

    def __init__(self):
        pass

    @keyword
    def verify_text(self, text):
        time.sleep(2)
        ele = self._get_element("")
        ele_txt = ele.get_element().get_attribute("innerText")
        BuiltIn().log("Expected value : " + text, level="INFO")
        BuiltIn().log("Real value : " + ele_txt, level="INFO")
        assert text in ele_txt

    @keyword
    def verify_text_alert_popups(self, text):
        time.sleep(3)
        xpath = '''//div[contains(@class,"p-toast-detail")and contains(text(), "{text}")]'''
        xpath = xpath.format(text=text)
        ele = self._get_element(xpath)


    @keyword
    def verify_texxt_span(self, text):
        time.sleep(3)
        if text == "Password length must contain at least 8 characters":
            xpath1 = '''//span[contains(@class,"msg")and contains(text(), "{text}")]'''
            xpath1 = xpath1.format(text=text)
            ele = self._get_element(xpath1)
        else:
            xpath = '''//div[contains(@class,"msg")and contains(text(), "{text}")]'''
            xpath = xpath.format(text=text)
            ele = self._get_element(xpath)

    @keyword
    def verify_text_in_table(self, label, text):
        time.sleep(3)
        xpath = '''//tbody//tr//td[contains(text(), "{label}")]/following-sibling::td//span[contains(text(), 
        "{text}")]'''
        xpath = xpath.format(label=label, text=text)
        ele = self._get_element(xpath)


    @keyword
    def verify_favorate_star(self,folder_file_name):
        xpath1 = '''//tr[td[normalize-space(text())="{folder_file_name}"] and td[i[contains(@class, 'fa-star') and contains(@style, 'color: rgb(255,215,0)')]]]'''
        xpath1 = xpath1.format(folder_file_name=folder_file_name)
        etoile_element1 = self._get_driver().find_element_by_xpath(xpath1)


    @keyword
    def verify_unfavorate_star(self,folder_file_name):

        xpath = '''//tr[td[normalize-space(text())="{folder_file_name}"] and td[i[contains(@class, 'fa-star') and contains(@style, 'color: rgb(182, 182, 182)')]]]'''
        xpath = xpath.format(folder_file_name=folder_file_name)
        etoile_element = self._get_driver().find_element_by_xpath(xpath)



















