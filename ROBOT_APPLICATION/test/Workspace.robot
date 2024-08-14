
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
Add_Workspace
    Make Valid Login
    wait    2
    click button with Name    New Workspace
    type text in label    ajj    Workspace Name
    click button with Name    Save
    verify text alert popups    Workspace added
delete_workspace
    Make Valid Login
    wait    3
    deleteworkspace    ajj
    click button with Name    Yes
    verify text alert popups    Workspace deleted
add_owners_to_workspace
    Make Valid Login
    wait    4
    rightclic workspace    ALA
    wait    2
    click text    Configure
    click button with Name    Add Members
    select window    Users
    add owners to workspace    ged,ged    ads,Test
    click button with Name    Confirm
    clear selection
    click button with Name    Save
    verify text alert popups    Workspace saved
config_role_management
    Make Valid Login
    wait    5
    rightclic workspace    ALA
    wait    2
    click text    Configure
    click text    Role Management
    delete element by name in label    White List    slime lamine,azal malze
    delete element by name in label    Black List    siwar siwar,ged ads,ged Test
    select fieldset with name    Authorized Roles
    type text in label    ADMIN    Roles
    select item in list    ADMIN
    clear seletion fieldset
    select fieldset with name    White List
    type text in label    ALA JILANI    Members    
    select item in list    ALA JILANI
    clear seletion fieldset
    select fieldset with name    Black List
    type text in label    siwar siwar    Members    
    select item in list    siwar siwar
    clear seletion fieldset
    click button with Name    Save    2
    verify text alert popups    Workspace saved
Edit_Folder_file_details_permission
    Make Valid Login
    wait    3
    click text    ALA
    wait    2
    choose folder file and fct    click    cc
    click icon by class    fa-users-cog
    wait    2
    import details permission    
    add checkbox details permission table    DEG Impact    Type,Size  
    click button with Name    Save
    verify text alert popups    Details Permission Saved
edit_role_management
    Make Valid Login
    wait    5
    click text    ALA
    wait    2
    choose folder file and fct    click    cc
    click text    Role Management
    import operations permission    
    select checkbox role management table    DEG Impact    View
    click button with Name    Save
    verify text alert popups    Role Management Saved
delete_owners_from_workspace
    Make Valid Login
    wait    4
    rightclic workspace    ALA
    wait    2
    click text    Configure
    delete owners from workspace    slime,ged,ged    lamine,Test,ads
    click button with Name    Save
    verify text alert popups    Workspace saved
