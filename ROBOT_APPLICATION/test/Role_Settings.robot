
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
add_role
    Make Valid Login
    wait    3
    click text    Settings
    click text    Role List
    click button with Name    Add Role
    type text in label    stagiaire    Role
    mark role as admin or supadmin    Admin
    mark role as admin or supadmin    ${EMPTY}
    click button with Name    Save
    verify text alert popups    Role Saved
add_role_invalid(check;samerole or emptyfield)
    Make Valid Login
    wait    3
    click text    Settings
    click text    Role List
    click button with Name    Add Role
    type text in label    ${EMPTY}    Role
    click button with Name    Save
    verify text alert popups    Role label is mandatory
edit_role
    Make Valid Login
    wait    3
    click text    Settings
    click text    Role List
    select p table row with name    stagiaire
    click icon by class    pi-pencil
    clear table selection
    type text in label    ${EMPTY}    Role
    clear with backspace    15
    type text in label    newstagiaire    Role
    mark role as admin or supadmin    Admin
    click button with Name    Save
    verify text alert popups    Role Saved
delete_role
    Make Valid Login
    wait    3
    click text    Settings
    click text    Role List
    select p table row with name    newstagiaire
    click icon by class    pi-trash
    clear table selection
    click button with Name    Yes
    verify text alert popups    Role Deleted
