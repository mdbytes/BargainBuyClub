<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<div class="container-fluid bg-dark py-3">
<div class="container bg-dark text-white">

# Guide to stored procedures

-- ---------------------------------------------------------------------

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

## Table: users

<table>
<thead>
<tr>
<th>Procedure</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>get_users()</td>
<td>Data for all users</td>
</tr>
<tr>
<td>add_user(firstName,lastName,emailAddress,password,isAdmin)</td>
<td>Adds user to database</td>
</tr>
<tr>
<td>get_user_by_id(userId)</td>
<td>Returns user with integer id of userId</td>
</tr>
<tr>
<td>update_user(userId,firstName,lastName,emailAddress,password,isAdmin) </td>
<td>Updates user with unique integer id of userId</td>
</tr>
<tr>
<td>delete_user_by_id(userId)</td>
<td>Deletes user with unique integer id of userId</td>
</tr>
<tr>
<td>get_user_alerts(userId)</td>
<td>Returns all alerts with id of userId</td>
</tr>
</tbody>
</table>

## Table: stores

<table>
<thead>
<tr>
<th>Procedure</th>
<th>Returns</th>
</tr>
</thead>
<tbody>
<tr>
<td>get_stores()</td>
<td>Returns all store objects</td>
</tr>
<tr>
<td>add_store(storeName,storeUrl,priceQuery,productNameQuery)</td>
<td>Adds store object</td>
</tr>
<tr>
<td>get_store_by_id(storeId)</td>
<td>Returns store object with unique integer id storeId</td>
</tr>
<tr>
<td>update_store(storeId,storeName,storeUrl,priceQuery,productNameQuery)</td>
<td>Updates store with unique integer id, storeId</td>
</tr>
<tr>
<td>delete_store_by_id(storeId)</td>
<td>Deletes store object with unique integer id, storeId</td>
</tr>
</tbody>
</table>

## Table: products

<table>
<thead>
<tr>
<th>Procedure</th>
<th>Returns</th>
</tr>
</thead>
<tbody>
<tr>
<td>get_products()</td>
<td>Returns all product objects</td>
</tr>
<tr>
<td>add_product(storeId, productUrl)</td>
<td>Adds product object</td>
</tr>
<tr>
<td>get_product_by_id(productId)</td>
<td>Returns product object with unique integer id productId</td>
</tr>
<tr>
<td>update_product(productId,storeId, productUrl)</td>
<td>Updates product with unique integer id, productId</td>
</tr>
<tr>
<td>delete_product_by_id(productId)</td>
<td>Deletes product object with unique integer id, productId</td>
</tr>
</tbody>
</table>

## Table: alerts

<table>
<thead>
<tr>
<th>Procedure</th>
<th>Returns</th>
</tr>
</thead>
<tbody>
<tr>
<td>get_alerts()</td>
<td>Returns all alert objects</td>
</tr>
<tr>
<td>add_alert(productId, userId, alertPrice)</td>
<td>Adds alert object</td>
</tr>
<tr>
<td>get_alert_by_id(alertId)</td>
<td>Returns alert object with unique integer id, alertId</td>
</tr>
<tr>
<td>update_alert(alertId,productId, userId, alertPrice)</td>
<td>Updates alert with unique integer id, alertId</td>
</tr>
<tr>
<td>delete_alert_by_id(alertId)</td>
<td>Deletes alert object with unique integer id, alertId</td>
</tr>
</tbody>
</table>

</div>
</div>