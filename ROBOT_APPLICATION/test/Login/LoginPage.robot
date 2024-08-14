*** Settings ***
Library           SeleniumLibrary
Library           ../Config/BrowserOptions.py
Library           ../lib/Window.py

*** Variables ***
${proton_mail_url}    https://mail.protonmail.com/
${username_field_locator}    //input[@name='username']
${password_field_locator}      //input[@name='password']
${login_button_locator}    //button
${forgot_password_locator}    //div[contains(@class, 'container')]/span[contains(@class, 'psw')]
${reset_email_password_locator}    id:email-input
${gmail_email_field_locator}    //input[@type="email"]
${failed_page_name}    Error
${url}    https://gedtest.aderivatives.com
${success_page_name}    MCP Data Room
${valid_username}    alajilani
${valid_password}    ala1234jilani
*** Keywords ***
Open Login Page
    ${options}    get firefox options
    ${exec_path}    get firefox driver path
    open browser    ${url}     executable_path=${exec_path}    options=${options}   #ff_profile_dir=/home/achreftrabelsi/.mozilla/firefox/10nblqb2.default-release
    maximize
Fill Login Info
    wait    3
    [Arguments]    ${username}    ${password}
    input text    ${username_field_locator}    ${username}
    input text    ${password_field_locator}    ${password}

Click Login
    click button    ${login_button_locator}

Verify Login Success
    Wait Until Keyword Succeeds    2s    500ms    title should be    ${success_page_name}

Verify Login Failed
    Wait Until Keyword Succeeds    2s    500ms    title should be    ${failed_page_name}

Verify Remember Me Clicked Success
    close all browsers
    open login page
    verify login success

Verify Remember Me Not Clicked Success
    close all browsers
    open login page
    Wait Until Keyword Succeeds    2s    500ms    page should contain element    ${password_field_locator}
    close all browsers

Click Remember Me
    Click Element    ${remember_me_checkbox_locator}

Make Valid Login
    open login page
    fill login info    ${valid_username}    ${valid_password}
    click login
    Wait Until Keyword Succeeds    5s    500ms    title should be    ${success_page_name}

Click Forgot Password
    click element    ${forgot_password_locator}
