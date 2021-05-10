## Sample wallet application, done as part of zolve assignment
### Dependencies required are included in the zolvewallet/pom.xml, all of which will be installed automatically booting apring app it with eclipse/spring tool suite.
### Included the postman queries and results as screenshot for reference. Port is 8080.
### Logs can be found at zolvewallet/logs/test.log. Included screenshot for log as reference.

### Tasks:
1. Create Wallet for a User (wallet/create) (pass phoneNumber in body)
2. Credit money to wallet (/wallet/{id}/credit?creditAmount=200) (pass phoneNumber as id, creditAmount as parameter)
3. Debit money from wallet (/wallet/{id}/debit?debitAmount=200) (pass phoneNumber as id, debitAmount as parameter)
4. Get current Balance (wallet/{id})
5. Every Wallet should have minimum balance of 200.
6. Keep log of all transactions on a wallet
