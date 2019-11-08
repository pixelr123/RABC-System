# RABC-System

Simple RABC-System with OAuth2

Default Users: 

------------------------------------------------------------------------------
|    user            |     password         role       |       permission    | 
| ani@gmail.com      |       ani           Superuser   |    all permission   |
| sam@gmail.com      |     fedora           Admin      |      view permission|  
| jon@gmail.com      |     fedora           Admin      |        undefined    |
| alice@gmail.com    |      fedora          Superuser  |        undefined    |
------------------------------------------------------------------------------
Default Roles:
1. Superuser
2. Admin

Default Permissions:
1. view_permission
2. create_role
3. edit_role
4. view_role
5. delete_role
6. assign_permission_to_role
7. view_permission_by_role
8. create_users
9. edit_users
10. view_users
11. delete_users
12. assign_user_to_role
13. view_user_by_role
14. delete_permission
15. delete_resource
16. update_resource
17. create_resource
18. assign_resource_to_user
19. delete_resource_from_user


-- Generating Token -- 
POST: /oauth/token - For genrating the token

    Basic Auth:
        username: atul123
        password: atul@123
    
    Login:
      username: ani@gmail.com
      password: ani
      grant_type: password - same for all the users

    Pass the token in the header as - Authorization Bearer<token>

-- To assign permission --
GET: /permissions - for getting all the permissions
GET: /roles/{id}/permissions - for getting permissions by role
PUT: /roles/{id}/permissions - for assigning the permissions to role

    For assigning the permission to the users
        /roles/{id}/permissions: here permission will take an array of permissions id
        Example: 
                [
                    1,2,3
                ]

DELETE: /role/{id}/permissions - for deleting the assigned permissions to a role

-- Roles --
GET: /roles - for getting all the roles
DELETE: /roles{id} - for deleting the role
PUT: /roles/{id} - for update the role
POST: /roles - for adding a new role

-- Users --
GET: /users - for getting all the users
PUT: /users{id} - for updating the user.
DELETE: /user{id} - for deleting the user.
POST: /users - for adding a new user

-- For assigning role to user

GET: /roles/{id}/users - for getting the list of user by role
PUT: /roles/{id}/users - for assigning the role to a user

    For assigning the role to the user
        /roles/{id}/users: here users will take an array of users id
        Example: 
                [
                    1,2,3
                ]

DELETE: /roles/{id}/users - for deleting the assigned role from user

-- Resource --
User[Ani - Superuser] has all the permission to access the resource
User[sam - Admin] has only update_resource permission

For assigning the permision to the user to access the resource or delete the resource or assign the resource to user:

    You have to call this api /roles/{id}/permissions: and you have give the permission to that perticular user.

GET: /resources - for getting all the resources
PUT: /resources{id} - for updating the resources
DELETE: /resources/{id} - for deleting the resources
POST: /resources - for adding new rewsources

-- For assigning resource to user -- 

PUT: /resources{id}/users - for assigning resource to user
     Example:
        /resources{id}/users: here users will take an array for user id
        [
            1,2,3
        ]
DELETE: /resources/{id}/permission - for deleting the assigned resource form user
