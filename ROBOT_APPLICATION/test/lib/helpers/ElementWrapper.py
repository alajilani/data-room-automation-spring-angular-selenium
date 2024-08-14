from selenium.webdriver.support import expected_conditions as EC

from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.webelement import FirefoxWebElement
from selenium.webdriver.support.wait import WebDriverWait
from robot.libraries.BuiltIn import BuiltIn
from selenium.webdriver.firefox.webdriver import WebDriver

import time



class ElementWrapper:
    def __init__(self, element, xpath):
        self.element: FirefoxWebElement = element
        self.xpath = xpath

    def click(self):
        element =WebDriverWait(self.get_driver(), 10).until(
            EC.element_to_be_clickable((By.XPATH, self.xpath))
        )
        self.get_driver().implicitly_wait(1)
        time.sleep(1)
        element.click()

    def send_keys(self, text):
        element=WebDriverWait(self.get_driver(), 10).until(
            EC.presence_of_element_located((By.XPATH, self.xpath)))
        time.sleep(1)
        element.send_keys(text)

    def wait_for_presence(self):
        return WebDriverWait(self.get_driver(), 10).until(
            EC.presence_of_element_located((By.XPATH, self.xpath)))
    def wait_for_text(self,text):
        return WebDriverWait(self.get_driver(), 10).until(
            EC.text_to_be_present_in_element_value((By.XPATH, self.xpath),text))

    def get_element(self):
        return self.element

    def get_driver(self):
        driver: WebDriver = BuiltIn().get_library_instance('seleniumlibrary').driver
        return driver
    def _button_check(self,driver):
        return True
