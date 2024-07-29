# User Management
Create User: POST /api/users
Get User by ID: GET /api/users/{userId}
Update User: PUT /api/users/{userId}
Delete User: DELETE /api/users/{userId}
Get All Users: GET /api/users

# Items
Get Product by ID: GET /api/items/{itemId}
Get All Products: GET /api/items

# Inventory Management
Create Product: POST /api/items
Update Product: PUT /api/items/{itemId}
Delete Product: DELETE /api/items/{itemId}

# Purchases
Create Order: POST /api/users/{userId}/purchases
Get All Orders: /api/users/{userId}/purchases
Get Order by ID: GET /api/users/{userId}/purchases/{id}