

# RTLog Util
A small and simple library for handling data in RTLog format. Made for handling Retail Sales Audit data. Supports FHEAD, THEAD, TITEM, IGTAX, IDISC, TTEND, TTAIL, TOTAL, DCLOSE and FTAIL records.

RTLog Util can be imported to an existing project or used directly in a terminal, with och without a graphical user interface.

### Run RTLog Developer Desktop 
Run in unix terminal: `> java -cp rtlog_developer-1.0.jar RTLogDeveloper <path-to-rtlog-file>`

##### OPTIONS

* `--print` Prints the RTLog file as Json to std. out.
* `--findField <field>` Prints out all values of field `<field>`.
* `--findContent <content>` Prints out all fields that contains `<content>`.
* `--fields <record>` Prints out all fields for a given record.
* `--diff <path-to-file>` Prints out all fields and values for the fields that differs between the two files.
* `--modify <line-sequence> <field> <new-value>` Creates a copy of the file with updated value. Changes the value of field `<field>` on line `<line-sequence>` 

### Run RTLog Developer Desktop 
Download the `.jar` file called `rtlog-developer-0.1.0` and dubble click on the icon. The image below is a screen shot from the desktop application.

![RTLog Developer](http://i.imgur.com/BPloDRD.png  =150x)



### Import RTLog Util

