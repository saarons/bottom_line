Bottom Line
===========
*bottom_line* is a transactional netting program for Hadoop.

Usage
-----
*bottom_line* takes a single tab delimited file of transactions and their amounts.  For example:
    1->2  500.00
    2->1  600.00
    3->1  700.00
    
Then run the program with the following command:
    hadoop jar transaction.jar Transaction <input file> <output directory>

The output file generated will represent the net effect on each account.  For example using the above input would generate:
    1 800.00
    2 -100.00
    3 -700.00
