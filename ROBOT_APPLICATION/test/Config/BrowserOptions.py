from robot.libraries.BuiltIn import BuiltIn
from selenium.webdriver.firefox.webdriver import WebDriver
from webdriver_manager.firefox import GeckoDriverManager
from selenium import webdriver
from shutil import which
from webdriver_manager.chrome import ChromeDriverManager

import os



def get_firefox_options():
    options = webdriver.FirefoxOptions()
    #options.profile = "/home/achreftrabelsi/.mozilla/firefox/10nblqb2.default-release"
    options.binary=which("firefox")
    options.headless = False


    return options

def exit():
    _get_driver().close();

def get_firefox_driver_path():
    os.environ['GH_TOKEN'] = "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"
    return GeckoDriverManager().install()

def setup():
    _get_driver().implicitly_wait(10)

def _get_driver():
    driver: WebDriver = BuiltIn().get_library_instance('seleniumlibrary').driver
    return driver

def maximize():
    _get_driver().set_window_size(4000,4000)
def get_chrome_driver_path():
    return ChromeDriverManager().install()
