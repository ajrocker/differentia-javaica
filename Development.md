See [Roadmap](Roadmap.md)

## Updating grammar ##

In case of updating `Java.g` grammar file:

  * put new version in `src/main/java/pl/ncdc/differentia/antlr`
  * compare contents with SVN and put `pl.ncdc.differentia.antlr` package in case of parser and lexer.
  * run maven with `generate-java-parser` enabled

New parser and lexer will be generated. All files should be commited to SVN then.