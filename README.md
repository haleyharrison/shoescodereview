# Project Name: Shoes

TODO: Write a project description

## Installation

TODO: Describe the installation process

First you open a terminal window and type in "posgres"

Then you open a different one and enter command "psql"

Then you enter command "CREATE DATABASE shoes;"

Then you open a different terminal window, where you're in a bash terminal, and you
get into the master directory here, then you enter command:

    pg_dump shoes < shoes.sql

Then you go back into the psql terminal and you enter command
      "CREATE DATABASE shoes_test WITH template shoes;"

Then you run the program with "gradle run" and test with "gradle test" in the bash terminal

## Usage

TODO: Write usage instructions

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license
