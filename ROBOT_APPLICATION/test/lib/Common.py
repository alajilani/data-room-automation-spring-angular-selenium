from robot.libraries.BuiltIn import BuiltIn
from selenium.webdriver.firefox.webdriver import WebDriver
from selenium.webdriver.firefox.webelement import FirefoxWebElement

from helpers.ElementWrapper import ElementWrapper
from Selection import Selection


class Common:

    def __init__(self):
        pass

    def _get_selection_library(self):
        selection_lib: Selection = BuiltIn().get_library_instance('Selection')
        return selection_lib
    def _get_driver(self):
        driver: WebDriver = BuiltIn().get_library_instance('seleniumlibrary').driver
        return driver

    def _get_element(self, xpath):
        i=0
        for i in range(len(xpath)):
            if(xpath[i]!="("):
                break
        str1 = xpath[:i]
        str2 = xpath[i:]
        xpath = str1 + self._get_selection_library()._create_xpath() + str2
        ele: FirefoxWebElement = self._get_driver().find_element_by_xpath(xpath)
        print(ele)
        return ElementWrapper(ele, xpath)

