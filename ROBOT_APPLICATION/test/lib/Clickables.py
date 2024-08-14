from operator import index

from Common import Common
import time
from selenium.webdriver.common.by import By
from robot.api.deco import keyword, library
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from selenium.common.exceptions import NoSuchElementException ,TimeoutException
from Inputs import Inputs


from helpers.ElementWrapper import ElementWrapper
@library(scope='TEST', version='1.0')
class Clickables(Common):

    def __init__(self):

        pass

    @keyword
    def click_text(self, name="", number="1"):
        xpath = self._generate_text_xpath(name, number)
        element = self._get_element(xpath)
        element.click()


    @keyword
    def deleteworkspace(self,workspace_name):
        xpath = self._generate_delete_workspace(workspace_name)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def click_icon_favorite(self, classname, number="1"):
        xpath = self._generate_icon_xpath_with(classname, number)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def click_icon_by_class(self, classname, number="1"):
        xpath = self._generate_icon_xpath_with(classname, number)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def click_button_with_Name(self, label=None, number="1"):
        xpath = self._generate_button_xpath_by_name(label, number)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def select_cretiria(self,criteria):
        xpath = self._generate_select_item_liste_deroulante(criteria)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def select_item_in_list(self,item_list):
        xpath = self._generate_select_item_liste_deroulante(item_list)
        element = self._get_element(xpath)
        element.click()




    @keyword
    def mark_role_as_admin_or_supadmin(self, label=""):
        if label == "":
            return  # Sortir de la fonction si label est une chaîne vide
        else:
            driver = self._get_driver()
            xpath = '''(//*[./label[normalize-space(text())="{label}"]]//p-checkbox)'''
            xpath = xpath.format(label=label)
            ele = driver.find_element(By.XPATH, xpath)
            ele.click()



    @keyword
    def rightclic_workspace(self, name):
        driver = self._get_driver()
        action = ActionChains(driver)
        xpath = '//div[contains(@class, "workspace-container")]//span[contains(@class, "workspace-name") and normalize-space(text()) = "{name}"]'.format(name=name)
        ele = driver.find_element(By.XPATH, xpath)
        action.context_click(ele).perform()


    def doubleclick(self, name, number="1"):
        driver = self._get_driver()
        action = ActionChains(driver)
        xpath = '(//*[normalize-space(text())="{name}"])[{number}]'.format(name=name, number=number)
        ele = driver.find_element(By.XPATH, xpath)
        action.double_click(ele).perform()  # Utilisation correcte de double_click() au lieu de double_Click()




    @keyword
    def add_checkbox_details_permission_table(self, role_name,details_list):

        # Obtenir les XPaths pour les permissions spécifiées
        xpaths = self.select_checkbox_table(role_name,details_list)

        for xpath in xpaths:
            try:

                # Chercher l'élément correspondant à chaque XPath
                element = self._get_element(xpath)


                element.click()
            except Exception as e:
                element = self._get_element(xpath)
    @keyword
    def edit_checkbox_details_permission_table(self, role_name, details_list):

        # Obtenir les XPaths pour les permissions spécifiées
        xpaths = self.edit_checkbox_table(role_name,details_list)

        for xpath in xpaths:
            try:
                # Chercher l'élément correspondant à chaque XPath
                element = self._get_element(xpath)

                element.click()
            except Exception as e:
                element = self._get_element(xpath)

    @keyword
    def click_edit_profile(self,profil_field,fct_name):
        xpath = self.click_edit_profile_fct(profil_field,fct_name)
        element = self._get_element(xpath)
        element.click()

    @keyword
    def delete_element_by_name_in_label(self, management_label="", Role_or_members=""):
        if  management_label=="" and  Role_or_members=="":
            print("vous n'avez pas choisi aucun role et aucun membre à supprimer.")
            return 1
        elif management_label=="":
            raise ValueError(" veuillez saisir le management_label. Les valeurs autorisées sont : [White List", "Black List", "Authorized Roles]")
        elif  Role_or_members=="" :
            raise ValueError("veuillez saisir la liste des roles et membres à supprimer ")


        # Convertir Role_or_members en liste
        Role_or_members = Role_or_members.split(',')


        # Définir les valeurs autorisées pour management_label
        allowed_labels = ["White List", "Black List", "Authorized Roles"]

        # Vérifier si management_label est dans les valeurs autorisées
        if management_label and management_label not in allowed_labels:
            raise ValueError(
                f"Valeur invalide pour management_label. Les valeurs autorisées sont : {', '.join(allowed_labels)}.")

        for role_or_member in Role_or_members:
            # Construire le XPath pour localiser l'élément à supprimer
            xpath = self.delete_element_byname(management_label, role_or_member)

            try:
                # Trouver et cliquer sur le bouton de suppression
                delete_button = self._get_element(xpath)
                delete_button.click()
                print(f"Élément '{role_or_member}' supprimé avec succès.")
            except Exception as e:
                # Lever une exception avec un message détaillé si l'élément n'est pas trouvé ou si une erreur se produit
                raise ValueError(
                    f"Cet élément '{role_or_member}' n'existe pas, donc impossible de le supprimer. ")

    def _generate_delete_workspace(self,workspace_name):
        xpath = '''//div[contains(@class, 'workspace-row') and .//span[normalize-space(text())='{workspace_name}']]//i[contains(@class, 'delete-workspace-btn')]'''

        xpath = xpath.format(workspace_name=workspace_name)

        return xpath

    def _generate_icon_xpath_with(self, divclass, number):
        # Vérifier la condition et retourner le XPath approprié
        if divclass == 'p-menubar-root-list':
            xpath2 = '''(//p-menubarsub//ul[contains( @class ,'{divclass}')]/li)[3]'''
            xpath2 = xpath2.format(divclass=divclass)
            return xpath2
        elif divclass == 'pi-check':
            xpath11 = '''(//span[contains(@class,'{divclass}')])'''
            xpath11 = xpath11.format(divclass=divclass)
            return xpath11
        elif divclass == 'pi-pencil':
            xpath1 = '''(//span[contains(@class,'{divclass}')])'''
            xpath1 = xpath1.format(divclass=divclass)
            return xpath1
        elif divclass == 'pi-trash':
            xpath0 = '''(//span[contains(@class,'{divclass}')])'''
            xpath0 = xpath0.format(divclass=divclass)
            return xpath0
        elif divclass == 'fa-folder-plus':
            xpath3 = f'''(//i[contains(@class,'{divclass}')])'''
            xpath3 = xpath3.format(divclass=divclass)
            return xpath3
        elif divclass == 'fa-paste':
            xpath4 = '''(//i[contains(@class,'{divclass}')])'''
            xpath4 = xpath4.format(divclass=divclass)
            return xpath4

        elif divclass == 'fa-file-upload':
            xpath5 = '''(//i[contains(@class,'{divclass}')])'''
            xpath5 = xpath5.format(divclass=divclass)
            return xpath5

        else:
            xpath_i = '''(//i[contains(@class,'{divclass}')][{number}])'''
            xpath_i = xpath_i.format(divclass=divclass, number=number)
            return xpath_i

    def _generate_text_xpath(self, name, number):
        xpath = '''(//*[normalize-space(text())="{name}"])[{number}]'''
        xpath = xpath.format(name=name, number=number)

        return xpath


    def _generate_button_xpath_by_name(self, name, number):
        xpath = "(//button[@label='{name}'])[{number}] "
        xpath = xpath.format(name=name, number=number)
        print(xpath)
        return xpath


    def _generate_select_item_liste_deroulante(self,criteria):
        xpath = "(//li//span[normalize-space(text())='{criteria}'])"
        xpath = xpath.format(criteria=criteria)
        print(xpath)
        return xpath





    @keyword
    def select_checkbox_role_management_table(self, role_name,permissions_list):

        permissions_list = permissions_list.split(',')  # Convertir les arguments en liste

        # Mapping des fonctions aux index de colonne
        function_index_map = {
            "View": 2,
            "Delete": 3,
            "Rename": 4,
            "Copy": 5,
            "Move File": 6,
            "Edit Process": 7,
            "Edit Activity":8,
            "Edit Sharing": 9,
            "Edit Event": 10,
            "Download": 11,
            "Update": 12,
            "Edit Details": 13,
            "Upload Files": 14,
            "Add Folder": 15,
            "File Request": 16,
            "File History": 17,
            "File Discussion": 18
        }

        # Création de la liste des index pour les fonctions fournies
        indexes = []
        if not role_name and not permissions_list:
            print("tu nas pas choisi   un rôle et des opérations.")
            return 1
        elif not role_name:
            raise ValueError("Veuillez choisir un rôle.")
        elif not permissions_list:
            raise ValueError("Veuillez choisir des opérations.")

        for member_function in permissions_list:
            index = function_index_map.get(member_function)
            if index is None:
                raise ValueError(f"ces operations '{member_function}' n'existent pas")
            indexes.append(index)


        for index in indexes:
            xpath = f'''(//p-table)//tr[td[1][normalize-space(text()) = "{role_name}"]]/td[{index}]//p-checkbox[not(@disabled)]'''
            try:
                element = self._get_element(xpath)
                element.click()
            except NoSuchElementException :
                raise ValueError(f"tu n'as pas le droit de choisir ces operations' .")


    def delete_element_byname(self,management_label,Role_or_members):

        xpath = '''//p-fieldset[@legend='{management_label}']//ul[contains(@class, 'p-autocomplete-multiple-container')] 
               //li[span[contains(@class, 'p-autocomplete-token-label') and normalize-space(text()) = '{Role_or_members}']]//span[contains(@class, 'pi-times')]'''

        xpath = xpath.format(management_label=management_label, Role_or_members=Role_or_members)

        return  xpath
    def click_edit_profile_fct(self,profil_field,fct_name):

        xpath='''//tbody//tr//td[normalize-space(text())='{profil_field}']/following-sibling::td/a[normalize-space(text())='{fct_name}']'''
        xpath = xpath.format(profil_field=profil_field, fct_name=fct_name)
        return xpath

    def right_clic_folder_file(self, file_folder_name):
        driver = self._get_driver()
        action = ActionChains(driver)
        xpath = '(//tr//*[normalize-space(text())="{file_folder_name}"])'.format(file_folder_name=file_folder_name)
        ele = driver.find_element(By.XPATH, xpath)
        action.context_click(ele).perform()

    def select_checkbox_table (self, role_name="",details_list=""):

        details_list = details_list.split(',')

        details_index_map = {
            "Type": 2,
            "Size": 3,
            "Creation Date": 4,
            "Created By": 5,
            "Subject": 6,
            "Description": 7,
            "Key Words/Tags": 8,
            "Modification Date": 9,
            "Privacy": 10,
            "Access Date": 11,
            "Category": 12,
            "Last Version": 13,
            "Comment": 13,
            "Owner": 15,
            "Parent Folder": 16
        }



        # Création de la liste des index pour les permissions fournies
        td_indexes = []
        if not role_name=="" and details_list=="":
            print("tu nas pas choisi un rôle et des permissions de details .")
            return 1
        elif  role_name=="":
            raise ValueError("Veuillez choisir un rôle.")
        elif  details_list=="":
            raise ValueError("Veuillez choisir des permissions de details.")
        for detail in details_list:
            # Recherche de l'index correspondant pour chaque permission
            td_index = details_index_map.get(detail)
            if td_index is None:
                raise ValueError(f"ces permissions de details '{detail}'  n'existent pas")
            td_indexes.append(td_index)

        # Génération de l'expression XPath avec les indexes de colonnes
        xpaths = []
        for td_index in td_indexes:
            xpath = '''(//p-table)[2]//tr[td[1][normalize-space(text()) = "{role_name}"]]/td[{td_index}]//p-checkbox'''
            xpaths.append(xpath.format(role_name=role_name, td_index=td_index))

        return xpaths

    def edit_checkbox_table(self, role_name="",details_list=""):

        details_list = details_list.split(',')

        permission_index_map = {
            "Creation Date": 2,
            "Created By": 3,
            "Privacy": 4,
            "Owner": 5,
            "Parent Folder": 6
        }

        # Création de la liste des index pour les permissions fournies
        td_indexes = []
        if not role_name=="" and  details_list=="":
            print("tu nas pas choisi un rôle et des permissions de details .")
            return 1
        elif  role_name=="":
            raise ValueError("Veuillez choisir un rôle.")
        elif  details_list=="":
            raise ValueError("Veuillez choisir des permissions de details.")
        for detail in details_list:
            # Recherche de l'index correspondant pour chaque permission
            td_index = permission_index_map.get(detail)
            if td_index is None:
                raise ValueError(f"ces permissions de details '{detail}'  n'existent pas")
            td_indexes.append(td_index)

        # Génération de l'expression XPath avec les indexes de colonnes
        xpaths = []
        for td_index in td_indexes:
            xpath = '''(//table)[5]//tr[td[1][normalize-space(text()) = "{role_name}"]]/td[{td_index}]//p-checkbox'''
            xpaths.append(xpath.format(role_name=role_name, td_index=td_index))

        return xpaths

    @keyword
    def add_owners_to_workspace(self, firstname_list="", lastname_list=""):
        if  firstname_list=="" or  lastname_list=="":
            raise ValueError("Veuillez entrer à la fois les prénoms et les noms de famille des utilisateurs.")

        # Convertir les paramètres en listes
        firstname_list = [name.strip() for name in firstname_list.split(',')]
        lastname_list = [name.strip() for name in lastname_list.split(',')]

        # Vérifier que les deux listes ont la même longueur
        if len(firstname_list) != len(lastname_list):
            raise ValueError("La liste des prénoms et des noms doivent avoir la même longueur.")

        total_users = len(firstname_list)
        all_users = set((firstname_list[i], lastname_list[i]) for i in range(total_users))
        found_users = set()

        while True:
            users_checked = 0

            while users_checked < total_users:
                firstname = firstname_list[users_checked]
                lastname = lastname_list[users_checked]

                # Construire les XPath
                xpath = f'(//p-table)[4]//tr[td[normalize-space(text())="{firstname}"] and td[normalize-space(text())="{lastname}"]]'
                xpath_checkbox = f'(//p-table)//tr[td[normalize-space(text())="{firstname}"] and td[normalize-space(text())="{lastname}"]]//p-checkbox'
                xpath_next = '//button[contains(@class, "p-paginator-next") and not(contains(@class, "p-disabled"))]'

                try:
                    # Essayer de trouver l'élément dans la table
                    element = self._get_driver().find_element(By.XPATH, xpath)
                    print(f"Element with firstname '{firstname}' and lastname '{lastname}' found.")

                    # Enregistrer l'utilisateur trouvé
                    found_users.add((firstname, lastname))

                    # Cliquer sur le checkbox associé
                    checkbox = self._get_driver().find_element(By.XPATH, xpath_checkbox)
                    checkbox.click()
                    print(f"Checked and clicked the checkbox for '{firstname} {lastname}'.")
                except NoSuchElementException:
                    print(f"Element with firstname '{firstname}' and lastname '{lastname}' not found.")

                users_checked += 1

            # Vérifiez si nous avons vérifié tous les utilisateurs dans la table actuelle
            try:
                next_button = self._get_driver().find_element(By.XPATH, xpath_next)
                next_button.click()
                print("Moving to the next page in the current table.")
                # Réinitialiser la variable users_checked pour vérifier les utilisateurs sur la nouvelle page
                users_checked = 0
            except NoSuchElementException:
                print("No more pages in the current table. Moving to the next table.")
                users_not_found = all_users - found_users
                if users_not_found:
                    raise ValueError(
                        f"Attention, les utilisateurs suivants n'ont pas été trouvés dans aucune table : {', '.join([f'{fn} {ln}' for fn, ln in users_not_found])}  "
                        f"donc l'ajout des utilisateurs est impossible.")
                else:
                    break

        # Vérifier les utilisateurs non trouvés

    @keyword
    def choose_folder_file_and_fct(self,fonction="",path_folder_name=""):

        if  fonction==""  :
            raise ValueError("Veuillez entrer la foction qui sera appliquée au fichier ou dossier.")




        path_folder_name = path_folder_name.split('/')
        print(f"Function argument 'fonction': '{fonction}'")  # Afficher la valeur de 'fonction'

        for index, file_folder_name in enumerate(path_folder_name):
            # Construire le XPath pour l'élément actuel
            xpath = f'//tr//*[normalize-space(text())="{file_folder_name}"]'

            try:
                # Essayer de trouver l'élément actuel dans la table
                element = self._get_driver().find_element(By.XPATH, xpath)
                print(f"Element '{file_folder_name}' found.")

                # Vérifier si c'est le dernier élément
                if index == len(path_folder_name) - 1:
                    # Pour le dernier élément, appliquer l'action spécifiée par 'fonction'
                    if fonction == 'right_click':
                        self.right_clic_folder_file(file_folder_name)
                    elif fonction == 'enter':
                        self.doubleclick(file_folder_name)
                    elif fonction == 'click':
                        xpath4 = f'//tr//*[normalize-space(text())="{file_folder_name}"]'
                        el = self._get_element(xpath4)
                        el.click()
                    elif fonction == 'find':
                        xpath3= f'//tr//*[normalize-space(text())="{file_folder_name}"]'
                        elm3 = self._get_driver().find_element(By.XPATH, xpath3)

                else:
                    # Pour les éléments intermédiaires, utiliser doubleclick
                    self.doubleclick(file_folder_name)

                # Si ce n'est pas le dernier élément, préparer le XPath du prochain élément
                if index < len(path_folder_name) - 1:
                    next_file_folder_name = path_folder_name[index + 1]
                    xpath2 = f'//tr//*[normalize-space(text())="{next_file_folder_name}"]'

                    try:
                        # Vérifier si le prochain élément existe
                        elm2 = self._get_driver().find_element(By.XPATH, xpath2)
                        print(f"Next element '{next_file_folder_name}' found.")
                    except NoSuchElementException:
                        print(f"Next element '{next_file_folder_name}' not found.")
                        break

            except NoSuchElementException:
                print(f"Element '{file_folder_name}' not found.")
                break

        print(f"Function argument 'fonction': '{fonction}'")

    @keyword
    def delete_categories(self, list_of_categories):
        list_of_categories = list_of_categories.split(',')

        for index, category in enumerate(list_of_categories):
            xpath = f'//p-treetable//tr//*[normalize-space(text())="{category}"]'

            try:
                # Essayer de trouver l'élément actuel dans la table
                element = self._get_driver().find_element(By.XPATH, xpath)
                if element:
                    # Construire le XPath pour l'icône de suppression
                    xpath1 = f'//p-treetable//tr[td[contains(text(), "{category}")]]//td[last()]/i[contains(@class, "fa-trash-alt")]'
                    delete = self._get_driver().find_element(By.XPATH, xpath1)
                    delete.click()

            except NoSuchElementException:
                print(f"Element '{category}' not found.")
                # Optionnel : continue pour essayer avec la prochaine catégorie ou gérer l'erreur autrement
                element = self._get_driver().find_element(By.XPATH, xpath)
    @keyword
    def import_details_permission(self, details_group_name=""):
        xpath1 = '//*[normalize-space(text())="Import Permission Group"]'
        xpath_details = f"//li//span[normalize-space(text())='{details_group_name}']"

        if details_group_name == '':
            print("Tu n'as pas importé un group de détails de permission.")
            return 1
        else:
            try:
                # Trouver et cliquer sur l'élément Import Permission Group
                import_element = self._get_driver().find_element(By.XPATH, xpath1)
                import_element.click()

                # Attendre 2 secondes
                time.sleep(2)

                # Trouver et utiliser l'élément spécifique basé sur details_name
                detail_element = self._get_driver().find_element(By.XPATH, xpath_details)
                detail_element.click()
            except NoSuchElementException:
                raise(f"details group name '{details_group_name}' nexiste pas dans la liste .")



    @keyword
    def import_operations_permission(self, operation_group_name=""):
        xpath1 = '//*[normalize-space(text())="Import Permission Group"]'
        xpath_operation = f"//li//span[normalize-space(text())='{operation_group_name}']"

        if operation_group_name == "":
            print("Tu n'as pas importé un group d'operation de permission.")
            return 1
        else:
            try:# Trouver et cliquer sur l'élément Import Permission Group
                import_element = self._get_driver().find_element(By.XPATH, xpath1)
                import_element.click()

                # Attendre 2 secondes
                time.sleep(2)

                # Trouver et utiliser l'élément spécifique basé sur details_name
                detail_element = self._get_driver().find_element(By.XPATH, xpath_operation)
                detail_element.click()
            except NoSuchElementException:
                raise(f"operation group name '{operation_group_name}' nexiste pas dans la liste .")

    @keyword
    def advanced_search_more_than_creteria(self,Values,Operations,fonctions,Fields):
        Values = Values.split(',')
        Operations = Operations.split(',')
        fonctions = fonctions.split(',')
        Fields = Fields.split(',')


        max_len = max(len(Fields), len(Values), len(Operations))
        # Créez une instance de la classe Inputs
        inputs_instance = Inputs()102989

        # Itérer sur les indices jusqu'à la longueur maximale
        for i in range(max_len):
            if i < len(Fields):
                field = Fields[i]
                if i == 0:
                    self.click_text("Select a creteria")
                    self.select_cretiria(criteria=field)
                elif i>0:
                    self.click_text(name=Fields[i-1],number="2")
                    self.select_cretiria(criteria=field)


            if i < len(Operations):
                operation = Operations[i]
                self.click_text("Select Operation")
                self.select_cretiria(criteria=operation)

            if i < len(Values):
                if i==0:
                    value = Values[i]
                    inputs_instance.type_text_with_no_label(text=value, number=1)
                elif i>0:
                    value = Values[i]
                    inputs_instance.type_text_with_no_label(text=value, number=i+1)

            if i < len(fonctions):
                if i == 0:
                    fct=fonctions[i]
                    self.click_button_with_Name(label=fct, number="1")
                elif i > 0:
                    fct = fonctions[i]
                    self.click_button_with_Name(label=fct, number= i+1)


    @keyword
    def advanced_search_one_creteria(self,Value,Operation,Field):
        # Créez une instance de la classe Inputs
        inputs_instance = Inputs()


        self.click_text("Select a creteria")
        self.select_cretiria(criteria=Field)

        self.click_text("Select Operation")
        self.select_cretiria(criteria=Operation)
        inputs_instance.type_text_with_no_label(text=Value, number=1)

    @keyword
    def delete_owners_from_workspace(self,firstname_list, lastname_list):

        if firstname_list == "" or lastname_list == "":
            raise ValueError("Veuillez entrer à la fois les prénoms et les noms de famille des utilisateurs.")

            # Convertir les paramètres en listes
        firstname_list = [name.strip() for name in firstname_list.split(',')]
        lastname_list = [name.strip() for name in lastname_list.split(',')]

        # Vérifier que les deux listes ont la même longueur
        if len(firstname_list) != len(lastname_list):
            raise ValueError("La liste des prénoms et des noms doivent avoir la même longueur.")

        total_users = len(firstname_list)

        users_checked=0

        while users_checked < total_users:
            firstname = firstname_list[users_checked]
            lastname = lastname_list[users_checked]

            # Construire les XPath
            xpath1 = f'(//p-table)[2]//tr[td[normalize-space(text())="{firstname}"] and td[normalize-space(text())="{lastname}"]]'
            xpath_trash = f'(//p-table)[2]//tr[td[normalize-space(text())="{firstname}"] and td[normalize-space(text())="{lastname}"]]//span[contains(@class, "pi-trash")]'

            try:
                element = self._get_driver().find_element(By.XPATH, xpath1)
                print(f"Element with firstname '{firstname}' and lastname '{lastname}' found.")

                # Cliquer sur le checkbox associé
                delete = self._get_driver().find_element(By.XPATH, xpath_trash)
                delete.click()
                print(f"this user '{firstname} {lastname}' is deleted.")
            except NoSuchElementException:
                print(f"Element with firstname '{firstname}' and lastname '{lastname}' not found.")

            users_checked += 1




















