
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
Rename_Folder_File
    Make Valid Login
    wait    4
    click text    ALA
    choose folder file and fct    right_click    cc  aa
    wait    1
    click text    Rename
    type text in label    ${EMPTY}    New Name
    clear with backspace    7
    type text in label    aa    New Name
    click text    Save
    select p table row with name    aa
    verify text    aa
    verify text    Folder
    clear table selection
copy_paste_Folder_File
    Make Valid Login
    wait    4
    click text    ALA
    choose folder file and fct    right_click    cc/aa
    wait    1
    click text    Copy
    verify text alert popups    File is Copied
    click text    ALA
    choose folder file and fct    enter    Chat
    click icon by class    fas fa-paste
    verify text alert popups    Path past
advaned_search(more_than_creteria)
    Make Valid Login
    wait    2
    click text    Advanced Search
    click text    New Search
    select window    ADVANCED SEARCH
    advanced search more than creteria    aa,a    contains,contains    OR    Name,Category
    click button with Name    Search
    clear selection
    wait    10
upload_file(to be checked)
    Make Valid Login
    wait    2
    click text    ALA
    wait    1
    choose folder file and fct    enter    Chat
    click icon by class    file-upload
    click text    Upload File/zip
    file upload    /home/alajilani/run.bash    
    wait    8
    click button with Name    Save
    verify text alert popups    Success
invalid_creation_folder
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    enter    cc
    click icon by class    fa-folder-plus
    select window    Add Folder
    type text in label    ${EMPTY}    Folder Name    1
    wait    1
    click text    Save
    clear selection
    verify text alert popups    Folder must have a name
simple_search_folder_file
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    enter    
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
    clear table selection
upload_folder(server problem)
    Make Valid Login
    wait    2
    close window8    alaworkspace
    wait    1
    choose folder file and fct    enter
    click icon by class    file-upload
    close window8    Upload Folder
    folder upload    /home/alajilani/fars
    wait    3
    close window8    Save
    verify text alert popups    Success
creation_folder
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    enter    cc /aa
    wait    1
    click icon by class    fa-folder-plus
    type text in label    @@    Folder Name
    wait    1
    click button with Name    Save
    verify text alert popups    Folder added
    wait    2
suppression_fichier_dossier
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    right_click    cc/aa/@@
    wait    1
    click text    Delete
    click button with Name    Yes
    verify text alert popups    Folder/File deleted
advanced_search(one creteria)
    Make Valid Login
    wait    3
    click text    Advanced Search
    click text    New Search
    select window    ADVANCED SEARCH
    advanced search one creteria    aa    contains    Name
    click button with Name    Search
    clear selection
    wait    5
favorite_dossier_fichier
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    find    cc
    verify unfavorate star    cc
    click icon favorite    fa-star
    verify text alert popups    Marked As Favorite
download_file_folder
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    right_click    cc/aa 
    click text    Download
    verify text alert popups    File downloaded
unfavorite_dossier_fichier
    Make Valid Login
    wait    3
    click text    ALA
    choose folder file and fct    find    cc/aa
    verify favorate star    aa
    click icon favorite    fa-star
    verify text alert popups    Marked As unFavorite
save_new_advanced_search
    Make Valid Login
    wait    2
    click text    Advanced Search
    click text    New Search
    select window    ADVANCED SEARCH
    advanced search more than creteria    aa,aa    contains,equal    OR    Name,Category
    click button with Name    Save
    clear selection
    type text in label    op    Filter Name
    click button with Name    Save    
    verify text alert popups    Filter is saved
