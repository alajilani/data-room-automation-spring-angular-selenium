
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
edit_subcategory
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    select table row with name    stage
    click icon by class    pi-chevron-right
    clear table selection
    select table row with name    subcat1
    click icon by class    fa-edit
    clear table selection
    type text in label    ${EMPTY}    Category Name
    clear with backspace    10
    type text in label    100    Category Name
    click button with Name    Save    2
    click button with Name    Save
    verify text alert popups    Categories saved
add_category_invalid(same name)
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    click button with Name    Add Category
    type text in label    bbbb    Category Name
    click button with Name    Save    2
    verify text alert popups    Category already exists
add_category
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    click button with Name    Add Category
    type text in label    stage    Category Name
    click button with Name    Save    2
    click button with Name    Save
    verify text alert popups    Categories saved
edit_category
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    select table row with name    stage
    click icon by class    fa-edit
    clear table selection
    type text in label    ${EMPTY}    Category Name
    clear with backspace    10
    type text in label    stage1    Category Name
    click button with Name    Save    2
    click button with Name    Save
    verify text alert popups    Categories saved
delete_subcategory
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    select table row with name    stage
    click icon by class    pi-chevron-right
    clear table selection
    select table row with name    100
    click icon by class    fa-trash
    clear table selection
    click button with Name    Save
    verify text alert popups    Categories saved
delete_category
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    delete categories    chch,hh
    click button with Name    Save
    verify text alert popups    Categories saved
add_subcategory
    Make Valid Login
    wait    3
    click text    Settings
    click text    Category List
    select table row with name    stage
    click icon by class    fa-plus
    clear table selection
    type text in label    subcat1    SubCategory Name
    click button with Name    Save    2
    click button with Name    Save
    verify text alert popups    Categories saved
