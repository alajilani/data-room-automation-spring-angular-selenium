
*** Settings ***
Resource          Login/LoginPage.robot
Library           lib/Inputs.py
Library           lib/Clickables.py
Library           lib/Window.py

Library           lib/Selection.py
Library           lib/Verification.py

Test Teardown     Close All Browsers


*** Variables ***
*** Test Cases ***
Login_Valid
    Make Valid Login
    wait    1
    click icon by class    p-menubar-root-list
    click text    Log Out
    wait    3
    type text with no label    alajilani
    type password with no label    ala1234jilani
    wait    1
    click text    Sign In
    wait    2
Forget_Password_Failed
    Make Valid Login
    wait    3
    click icon by class    p-menubar-root-list
    click text    Log Out
    wait    3
    close window8     Forgot password?
    wait    2
    type text with no label    alajilani555    
    type text with name    ala.jilan11i@esprit.tn    email
    click text    Send New Password
    verify texxt span    Incorrect userName/email
Forget_Password
    Make Valid Login
    wait    3
    click icon by class    p-menubar-root-list
    click text    Log Out
    wait    3
    click text     Forgot password?
    wait    2
    type text with no label    alajilani    1
    type text with name    ala.jilani@esprit.tn    email
    click text    Send New Password
    wait    2
Login_No_Valid
    Make Valid Login
    wait    3
    click icon by class    p-menubar-root-list
    click text    Log Out
    wait    3
    type text with no label    alajilani4
    type password with no label    0000
    wait    2
    click text    Sign In
    verify texxt span    Incorrect login/password
