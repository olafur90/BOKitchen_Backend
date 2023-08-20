DELETE FROM available_categories;
DELETE FROM users;

INSERT INTO available_categories (icelandic_name, name, image_path)
VALUES
('Hakk', 'ground_beef', 'https://www.thedinnerbite.com/wp-content/uploads/2020/08/how-to-cook-ground-beef-img-4.jpg'),
('Kjöt', 'meat', 'https://www.tastingtable.com/img/gallery/how-to-quickly-elevate-your-steaks-flavor-after-its-done-cooking/intro-1658756324.jpg'),
('Kjúklingur', 'chicken', 'https://assets.epicurious.com/photos/57d1f0b2d6d221fb71355965/16:9/w_2000,h_1125,c_limit/salted-roast-chicken-BA-090816.jpg'),
('Fiskur', 'fish', 'https://cookingwithmammac.com/wp-content/uploads/2020/05/1200-sq-Oven-Fried-Cod-Image.jpg'),
('Pasta', 'pasta', 'https://static01.nyt.com/images/2020/02/05/dining/as-brown-butter-pasta-print/merlin_150298908_b992b854-cd91-4306-b1ad-5428bfad8614-superJumbo.jpg'),
('Súpur', 'soups', 'https://images.immediate.co.uk/production/volatile/sites/30/2017/11/Vegetable-soup-recipes-1eb6583.jpg?quality=90&resize=768,574'),
('Asískt', 'asian', 'https://media-cldnry.s-nbcnews.com/image/upload/newscms/2019_30/2943911/190723-easy-chow-mein-al-1414.jpg'),
('Mexíkóskt', 'mexican', 'https://www.studyspanishlatinamerica.com/blog/wp-content/uploads/2021/06/guide-to-popular-mexican-food.jpg'),
('Meðlæti', 'sidedish', 'https://assets.bonappetit.com/photos/5b106867cb25b938fafaaaca/4:3/w_2912,h_2184,c_limit/perfect-baked-potato.jpg'),
('Annað', 'other', '');

INSERT INTO users(email, first_name, last_name, password, username)
VALUES
('olafur1990@gmail.com', 'Ólafur', 'Pálsson', '123', 'olafur');