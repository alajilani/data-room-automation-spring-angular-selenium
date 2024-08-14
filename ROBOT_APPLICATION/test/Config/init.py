from robot.api import SuiteVisitor
from robot.api import ResultWriter

class RPConnection(SuiteVisitor):
    def __init__(self):
        self.writer = ResultWriter(
            RP_ENDPOINT, RP_TOKEN, RP_PROJECT, RP_LAUNCH
        )

    def start_suite(self, suite):
        self.writer.start_suite(suite)

    def end_suite(self, suite):
        self.writer.end_suite(suite)

    def start_test(self, test):
        self.writer.start_test(test)

    def end_test(self, test):
        self.writer.end_test(test)

    def start_keyword(self, keyword):
        self.writer.start_keyword(keyword)

    def end_keyword(self, keyword):
        self.writer.end_keyword(keyword)

RP_ENDPOINT = ""
RP_TOKEN = "your_token"
RP_PROJECT = "your_project_name"
RP_LAUNCH = "your_launch_name"

