This example app has a couple test endpoints.
It also runs using Postgres, so you will need to point it to a postgres instance, running one locally totally works.

Once it boots up, you can checkout
GET
http://localhost:8081/highscore?apiKey=hjk123hD1j

then do a PUT to the same endpoint, no apiKey necessary
{
    "username": "shamshirz",
    "game": "HoN",
    "score": 44,
    "date": [
      2016,
      3,
      19,
      17,
      6,
      11,
      576000000
    ]
}

and then do the GET again, vwallah?
