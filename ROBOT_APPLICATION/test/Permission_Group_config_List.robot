
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
save_new_details_permission
    Make Valid Login
    wait    3
    click text    ALA
    wait    3
    choose folder file and fct    click    cc
    click icon by class    fa-users-cog
    add checkbox details permission table    SUPER_ADMIN    Type,Size
    click button with Name    Save As Permission Group Config
    type text in label    adminperm    Permission Config Name
    click button with Name    Save
    verify text alert popups    Details Permission Group Saved
save_new_operation_management_permission
    Make Valid Login
    wait    3
    click text    ALA
    wait    3
    choose folder file and fct    click    cc
    click text    Role Management
    select checkbox role management table    SUPER_ADMIN    View,Delete
    click button with Name    Save As Permission Group Config
    type text in label    supadminroleperm    Permission Config Name
    click button with Name    Save
    verify text alert popups    Permission Group Saved
delete_folder_file_operations_permission
    Make Valid Login
    wait    3
    click text    Settings
    click text    Permission Group
    click text    Folder Operations Permission Group
    select table row with name    B
    click icon by class    pi-trash
    clear table selection
    click button with Name    Yes
    verify text alert popups    Permission Group deleted
delete_flder_file_details_permission
    Make Valid Login
    wait    3
    click text    Settings
    click text    Permission Group
    click text    Folder Details Permission Group
    select table row with name    pip
    click icon by class    pi-trash
    clear table selection
    click button with Name    Yes
    verify text alert popups    Permission Group deleted
edit_folder_file_details_permission( problem)
    Make Valid Login
    wait    3
    click text    Settings
    click text    Permission Group
    click text    Folder Details Permission Group
    select table row with name    aa
    click icon by class    pi-pencil
    clear table selection
    edit checkbox details permission table    ADMIN    Owner,Privacy
    click button with Name    Save
    verify text alert popups    Permission Group Saved
edit_folder_file_operations_permission
    Make Valid Login
    wait    3
    click text    Settings
    click text    Permission Group
    click text    Folder Operations Permission Group
    select table row with name    123
    click icon by class    pi-pencil
    clear table selection
    select checkbox role management table    DD team    Delete,View
    click button with Name    Save
    verify text alert popups    Permission Group Saved
