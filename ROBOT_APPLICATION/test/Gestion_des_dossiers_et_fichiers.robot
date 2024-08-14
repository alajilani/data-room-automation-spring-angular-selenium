
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
Renommer_Folder_File
    Make Valid Login
    wait    5
    click text    alaworkspace    
    wait    2
    rightclic    alla1    2
    click text    Rename
    type text in label    1    New Name
    clear with backspace    7
    type text in label    alla12    New Name
    click text    Save
    select p table row with name    alla12
    verify text    alla12
    clear table selection
copier_coller_fichier_dossier(tobe_checked)
    Make Valid Login
    wait    3
    type text with no label    aa    
    wait    4
    select p table row with name    aa
    click text    aa    1
    clear table selection
    click icon Name under div    left-toolbar    fa-copy
    verify text alert popups    File is Copied
    click text    alaworkspace    
    click icon Name under div    left-toolbar    fa-paste
    verify text alert popups    Path pasted
advaned_search(more_than_criteria)
    Make Valid Login
    wait    2
    liste deroulante    1
    click text    New Search
    select window    ADVANCED SEARCH
    click text    Select a creteria
    select cretiria    Uploaded By
    click text    Select Operation    
    select cretiria    equal
    wait    1
    type text with no label    ged Test    2
    click text    Uploaded By    2
    select cretiria    Name
    click text    Select Operation
    select cretiria    contains
    type text with no label    funds    4
    click button with Name    AND
    click text    Search
    wait    15
upload_file(server problem)
    Make Valid Login
    wait    2
    click text    My Work Space    2
    wait    2
    click icon Name under div    right-toolbar    file-upload
    wait    1
    click text    Upload File/zip
    file upload    /home/alajilani/aaaaa    
    wait    8
    click text    Save
    verify text alert popups    Success
    wait    2
invalid_creation_folder
    Make Valid Login
    wait    2
    click text    alaworkspace    
    wait    2
    click icon Name under div    right-toolbar    fas fa-folder-plus
    type text in label        Folder Name
    wait    1
    click text    Save
    verify text alert popups    Folder must have a name
    wait    2
simple_search_folder_file
    Make Valid Login
    wait    3
    click text    alaworkspace    
    wait    2
    type text for search    aa    Search
    wait    2
    select p table row with name    aa
    verify text    aa
    verify text    Folder
global_search_fichier(filterproblem)
    Make Valid Login
    wait    5
    type text for search    aa    Global Search
    wait    3
    select p table row with name    aa
    verify text    aa
    verify text    Folder
upload_folder(server problem)
    Make Valid Login
    wait    2
    click text    My Work Space    2
    wait    2
    click icon Name under div    right-toolbar    file-upload
    wait    1
    click text    Upload Folder
    folder upload    /home/alajilani/fars
    wait    4
    click text    Save
    verify text alert popups    Success
creation_folder
    Make Valid Login
    wait    2
    click text    My Work Space    2
    wait    1
    click icon Name under div    right-toolbar    folder-plus
    type text in label    @@    Folder Name
    wait    1
    click text    Save
    verify text alert popups    Success
    wait    2
suppression_fichier
    Make Valid Login
    close window8    10
    click text    alaworkspace    
    wait    2
    select p table row with name    aa
    verify text    Folder
    click text    aa    
    clear table selection
    click icon Name under div    left-toolbar    fas fa-trash
    wait    1
    click button with Name    Yes
    verify text alert popups    Folder/File deleted
advanced_search(one creteria)filter problem
    Make Valid Login
    close window8    10
    liste deroulante    1
    click text    New Search
    select window    ADVANCED SEARCH
    click text    Select a creteria
    select cretiria    Name
    click text    Select Operation
    select cretiria    contains
    wait    1
    type text with no label    aa    2
    click text    Search
    close window8    50
suppression_dossier
    Make Valid Login
    close window8    10
    click text    alaworkspace    
    select p table row with name    azs
    verify text    Folder
    clear table selection
    click text    azs    2
    click icon Name under div    left-toolbar    fa-trash
    wait    1
    click button with Name    Yes
    verify text alert popups    Folder/File deleted
favorite_dossier_fichier
    Make Valid Login
    close window8    20
    click text    alaworkspace    
    wait    1
    select p table row with name    alla1
    verify unfavorate star
    verify text    Folder
    click icon favorite    fa-star
    clear table selection
    verify text alert popups    Marked As Favorite
download_folder
    Make Valid Login
    close window8    10
    click text    alaworkspace    
    select p table row with name    lollo1
    verify text    Folder
    click text    lollo1    
    clear table selection
    click icon Name under div    left-toolbar    fa-download
    verify text alert popups    File downloaded
download_file
    Make Valid Login
    close window8    10
    click text    alaworkspace    
    select p table row with name    LoginPage.robot
    verify text    text File
    click text    LoginPage.robot    
    clear table selection
    click icon Name under div    left-toolbar    fa-download
    verify text alert popups    File downloaded
unfavorite_dossier_fichier
    Make Valid Login
    close window8    20
    click text    alaworkspace    
    wait    1
    select p table row with name    alla1
    close window8    
    verify text    Folder
    click icon favorite    fa-star
    clear table selection
    verify text alert popups    Marked As unFavorite
