# trading-web-application-BE







## spustit Docker containeri
```
docker compose up -d
```

## phpMyAdmin
http://localhost:3307/

## Java cesta
trading-application/src/main/java/com/umb/trading_software/TradingApplication.java


## java Verzia
openjdk 17.0.10 2024-01-16

## Open Api documentation
http://localhost:8080/swagger-ui/index.html

# symbol-by-id-controller

```
{
  "ctidTraderAccountId": 38265727,
  "symbolId": [1]
}
```


# symbols-list

```
{
  "ctidTraderAccountId": 38265727,
}
```

# trendbars

```
{
  "ctidTraderAccountId": 38265727,
  "fromTimestamp": 1707552774000,
  "period": "M30",
  "symbolId": 1,
  "toTimestamp": 1710058374000
}
```



Ctrader authetification
https://help.ctrader.com/open-api/account-authentication/#sending-an-http-request

Ctrader messages
https://help.ctrader.com/open-api/messages/#protooaaccountauthreq
