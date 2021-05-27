INSERT INTO library (name) VALUES
  ('Social Sciences Library'),
  ('The Royal Library of Belgium');

INSERT INTO book_family (name, library_id) VALUES
  ('Science & Math', 2),
  ('Technology', 1),
  ('History & Philosophy of Science', 1),
  ('Physics', 2),
  ('Engineering', 1);

INSERT INTO book (title, library_id, book_family_id) VALUES
  ('Clean Code', 1, 1),
  ('Humankind: A Hopeful History', 1, 3),
  ('The Big Roads: The Untold Story of the Engineers, Visionaries, and Trailblazers Who Created the American Superhighways', 1, 2),
  ('No Rules Rules: Netflix and the Culture of Reinvention', 1, 2),
  ('Behold a Pale Horse', 1, 4),
  ('The Human Cosmos: Civilization and the Stars', 1,4),
  ('Rocket Billionaires: Elon Musk, Jeff Bezos, and the New Space Race', 2, 1),
  ('Astrophysics for People in a Hurry', 2, 1),
  ('A Brief History of Time', 2, 1),
  ('There is No Place Like Space: All About Our Solar System', 2, 1);