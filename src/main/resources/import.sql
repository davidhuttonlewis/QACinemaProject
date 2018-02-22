INSERT INTO Screen (id, accessibility, numberOfSeats, type) VALUES (1, true, 50, 'THREE_D')
INSERT INTO Screen (id, accessibility, numberOfSeats, type) VALUES (2, false, 100, 'GOLD')
INSERT INTO Screen (id, accessibility, numberOfSeats, type) VALUES (3, true, 50, 'IMAX')

INSERT INTO Showing (id, film, time, screening_id) VALUES (1, 'harry potter', '2018-01-01 11:30:00', 1)
INSERT INTO Showing (id, film, time, screening_id) VALUES (2, 'minion', '1900-01-01 11:30:00', 1)
INSERT INTO Showing (id, film, time, screening_id) VALUES (3, 'star wars', '2018-01-01 18:30:00', 2)
INSERT INTO Showing (id, film, time, screening_id) VALUES (4, 'black panther', '2018-01-01 19:30:00', 3)
INSERT INTO Showing (id, film, time, screening_id) VALUES (5, 'hell', '2018-01-01 00:30:00', 3)

INSERT INTO Booking (id, BookingRef) VALUES (5, '004323')
INSERT INTO ticket (id, price, type, booking_id, showing_id) values (1, 7.00, 'STANDARD', 5, 3)
INSERT INTO ticket (id, price, type, booking_id, showing_id) values (2, 7.00, 'STANDARD', 5, 3)
