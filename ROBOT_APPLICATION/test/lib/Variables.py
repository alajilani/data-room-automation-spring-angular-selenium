from robot.api.deco import keyword, library

from Common import Common

@library(scope='TEST', version='1.0')
class Variables(Common):
    def __init__(self):
        pass

    @keyword
    def Get_Param(self, key, default_value):
        pass
    @keyword
    def Get_Test(self, key, default_value):
        pass
    @keyword
    def Get_Global_Param(self, key, default_value):
        pass
    @keyword
    def Get_Test_Param(self, key, default_value):
        pass



