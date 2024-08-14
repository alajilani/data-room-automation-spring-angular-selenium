from robot.api.deco import keyword, library
from robot.libraries.BuiltIn import BuiltIn
from selenium.webdriver.firefox.webdriver import WebDriver
from selenium.webdriver.firefox.webelement import FirefoxWebElement
from helpers.ElementWrapper import ElementWrapper
import time


@library(scope='TEST', version='1.0')
class Window:

    def __init__(self):
        pass
    @keyword
    def close_window8(self,window_name):
        xpath = '''//app-dynamic-window//div[normalize-space(text())="{window_name}"] //button[contains(@class, 'p-dialog-header-close')]'''.format(window_name=window_name)
        self._get_element(xpath).click()

    @keyword
    def wait(self,seconds):
        time.sleep(int(seconds))


       
    def _get_driver(self):
        driver: WebDriver = BuiltIn().get_library_instance('seleniumlibrary').driver
        return driver

    def _get_element(self, xpath):
        ele: FirefoxWebElement = self._get_driver().find_element_by_xpath(xpath)
        print(ele)
        return ElementWrapper(ele,xpath)

