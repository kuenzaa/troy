package troy.cql.parser.ddl

import troy.cql.ast.CqlParser._
import troy.cql.ast.CreateType
import troy.cql.ast.ddl.Field

trait CreateTypeParser {
  def createTypeStatement: Parser[CreateType] = {
    def fields = {
      val fieldParser = identifier ~ dataType ^^^^ Field
      parenthesis(rep1sep(fieldParser, ","))
    }

    val typeName = ".".i ~> identifier

    "CREATE TYPE".i ~>
      ifNotExists ~
      keyspaceName ~
      typeName ~
      fields ^^^^ CreateType.apply
  }
}
