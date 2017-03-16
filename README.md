

# RTLog Util
A small and simple library for handling data in RTLog format. Made for handling Retail Sales Audit data. Supports FHEAD, THEAD, TITEM, IGTAX, IDISC, TTEND, TTAIL, TOTAL, DCLOSE and FTAIL records.

RTLog Util can be imported to an existing project or used directly in a terminal, with och without a graphical user interface.

## Run RTLog Developer

#### In terminal
`$ java -cp rtlog_developer-0.1.0.jar RTLogDeveloper <path-to-rtlog-file>`

####With Maven
`$ mvn exec:java -Dexec.mainClass="RTLogDeveloperApplication" -Dexec.args="<path-to-rtlog-file>"`

##### OPTIONS

* `--print` Prints the RTLog file as Json to std. out.
* `--findField <field>` Prints out all values of field `<field>`.
* `--findContent <content>` Prints out all fields that contains `<content>`.
* `--fields <record>` Prints out all fields for a given record.
* `--diff <path-to-file>` Prints out all fields and values for the fields that differs between the two files.
* `--modify <line-sequence> <field> <new-value>` Creates a copy of the file with updated value. Changes the value of field `<field>` on line `<line-sequence>` 

## Run RTLog Developer Desktop version
Download `rtlog-developer-0.1.1.jar` and dubble click on the icon. The image below is a screen shot from the desktop application. You can also run the desktop version with a shell or with Maven, se details below.

####With Maven
`$ mvn exec:java -Dexec.mainClass="RTLogDeveloperApplication" -Dexec.args="gui"`

#### In terminal

`$ java -cp rtlog_developer-0.1.0.jar RTLogDeveloper gui` 

##Build

`$ mvn compile`

###Build executable jar

`$ mvn clean compile assembly:single` 

##Change log

 * **2017-03-16** `version 0.1.1` Added options to get error logs from RTLog Util. In desktop version, you can access the error logs with the button _Error log_.
