-- -----------------------------------------------------
-- Insert seed data into tables
-- -----------------------------------------------------

USE bargain_buy_club;

-- System updates

insert into system_updates (update_date, update_description)
VALUES ('2023-04-01', 'Database initiation');

-- Users

insert into users (first_name, last_name, email_address, password, is_admin)
values ('Martin', 'Dwyer', 'martin@mdbytes.com', '2d41206bee3804f8d36e82d892b58e37710edafdd3d281c7ae20344f56897f8d',
        'true');
insert into users (first_name, last_name, email_address, password, is_admin)
values ('Martin', 'Dwyer', 'martin.dwyer@outlook.com',
        'f2b0a2a7b5a5ac4d38b7cfee277f937c5be762414eb83003bfd53c32a0e2c57d', 'false');

-- stores

insert into stores (store_name, store_url, price_query, product_name_query)
values ('WebScraper API', 'https://webscraper.io', 'h4.pull-right.price', 'p.description');
insert into stores (store_name, store_url, price_query, product_name_query)
values ('Amazon.com', 'https://amazon.com', 'span.a-offscreen', '#productTitle');

-- products

insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/566',
        'Acer Aspire ES1-572 Black, 15.6" HD, Core i5-7200U, 4GB, 500GB, Linux', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/508',
        'IPS, Dual-Core 1.2GHz, 8GB, Android 4.3', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/579',
        'Lenovo ThinkPad L460, 14" FHD IPS, Core i7-6600U, 8GB, 256GB SSD, Windows 10 Pro', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (1, 'https://webscraper.io/test-sites/e-commerce/allinone/product/544',
        'Asus ROG Strix SCAR Edition GL503VM-ED115T, 15.6" FHD 120Hz, Core i7-7700HQ, 16GB, 256GB', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (2,
        'https://www.amazon.com/Lavalock-Nomex-gasket-smoker-stick/dp/B00JO00S04/ref=sr_1_1_sspa?crid=PBH8GHE86TJW&keywords=lavalock&qid=1681755474&sprefix=%2Caps%2C85&sr=8-1-spons&psc=1&smid=A262TPPYXMWGNT&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExOUdLNzRLS0dVUFFRJmVuY3J5cHRlZElkPUEwMjkzNTM5MUxGSVExUEtPQlc3UCZlbmNyeXB0ZWRBZElkPUEwMjM2ODUyU09INFlQRk4zOEtPJndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ==',
        '1/2" x 1/8" LavaLock High Temp BBQ Gasket Smoker Seal for Nomex Door Lid, Self Stick Grey', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (2,
        'https://www.amazon.com/Char-Griller-Smokin-Champ-Grill-Cover/dp/B07G4D59LZ/ref=sr_1_3?crid=2WILWR33DVM87&keywords=char+griller+smoker+cover&qid=1681758599&sprefix=char+griller+smoker+cove%2Caps%2C98&sr=8-3',
        'Char-Griller® Smokin Champ™ Grill Cover', 0.0, '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (2,
        'https://www.amazon.com/Char-Griller-1224-Smokin-Square-Charcoal/dp/B0009NU5YY/ref=sr_1_3?crid=11NP8LCB39ETE&keywords=char+griller+smoker&qid=1681758702&sprefix=char+griller+smoker+%2Caps%2C99&sr=8-3',
        'Char-Griller E1224 Smokin Pro 830 Square Inch Charcoal Grill with Side Fire Box, 50 Inch, Black', 0.0,
        '2023-04-13');
insert into products (store_id, product_url, product_name, recent_price, last_updated)
values (2,
        'https://www.amazon.com/AmazonBasics-Charcoal-Grill-Compact-Table/dp/B081D8DC93/ref=d_pb_allspark_dp_sims_pao_desktop_session_based_sccl_4_1/137-3174182-0385552?pd_rd_w=uyO6b&content-id=amzn1.sym.6b5008ac-c24a-4aea-a3ea-015a531184f5&pf_rd_p=6b5008ac-c24a-4aea-a3ea-015a531184f5&pf_rd_r=8CZVNZ7WBR010Q4Y6SHQ&pd_rd_wg=aKIQ6&pd_rd_r=e461e374-b089-4afb-810d-a54db294b258&pd_rd_i=B081D8DC93&psc=1',
        'Amazon Basics Charcoal Grill - Compact Size, Table Top Grill and Side Fire Box, Black', 0.0, '2023-04-13');


-- alerts

insert into alerts (product_id, user_id, alert_price)
values (1, 2, 300.0);
insert into alerts (product_id, user_id, alert_price)
values (2, 2, 100.0);
insert into alerts (product_id, user_id, alert_price)
values (3, 2, 800.0);
insert into alerts (product_id, user_id, alert_price)
values (4, 2, 1400.0);
insert into alerts (product_id, user_id, alert_price)
values (5, 1, 10.0);
insert into alerts (product_id, user_id, alert_price)
values (6, 1, 34.0);
insert into alerts (product_id, user_id, alert_price)
values (7, 1, 275.0);
insert into alerts (product_id, user_id, alert_price)
values (8, 1, 80.0);

