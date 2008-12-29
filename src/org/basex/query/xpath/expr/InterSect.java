package org.basex.query.xpath.expr;

import org.basex.data.Nodes;
import org.basex.query.QueryException;
import org.basex.query.xpath.XPContext;
import org.basex.query.xpath.item.Nod;

/**
 * Intersect Expression.
 * 
 * @author Workgroup DBIS, University of Konstanz 2005-08, ISC License
 * @author Tim Petrowsky
 */
public final class InterSect extends Arr {
  /**
   * Constructor.
   * @param e operands expression
   */
  public InterSect(final Expr... e) {
    super(e);
  }

  @Override
  public Nod eval(final XPContext ctx) throws QueryException {
    final Nod n = (Nod) (ctx.eval(expr[0]));
    int[] result = n.nodes;
    for(int i = 1; i != expr.length; i++) {
      result = Nodes.intersect(((Nod) (ctx.eval(expr[i]))).nodes, result);
    }
    return expr.length != 1 ? new Nod(result, ctx) : n;
  }

  @Override
  public String toString() {
    return toString(" & ");
  }
}
