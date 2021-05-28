INSERT INTO user (name, email, password) VALUES
  ('sa', 'sa@email.com', '$2a$10$yo9nRrztHF.58LZVIpnY0OD2A9CzIsZNQlXkMKyH9b9peLx4hDg7q');

INSERT INTO book_family (name) VALUES
  ('Science & Math'),
  ('Technology'),
  ('History & Philosophy of Science'),
  ('Physics'),
  ('Engineering');

INSERT INTO book (title, book_family_id) VALUES
  ('Clean Code', 1),
  ('Humankind: A Hopeful History', 3),
  ('The Big Roads: The Untold Story of the Engineers, Visionaries, and Trailblazers Who Created the American Superhighways', 2),
  ('No Rules Rules: Netflix and the Culture of Reinvention', 2),
  ('Behold a Pale Horse', 4),
  ('The Human Cosmos: Civilization and the Stars', 4),
  ('Rocket Billionaires: Elon Musk, Jeff Bezos, and the New Space Race', 1),
  ('Astrophysics for People in a Hurry', 1),
  ('A Brief History of Time', 1),
  ('There is No Place Like Space: All About Our Solar System', 1);