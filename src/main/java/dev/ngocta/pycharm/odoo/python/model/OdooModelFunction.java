package dev.ngocta.pycharm.odoo.python.model;

import com.jetbrains.python.PyNames;
import com.jetbrains.python.psi.PyCallSiteExpression;
import com.jetbrains.python.psi.PyFunction;
import com.jetbrains.python.psi.impl.PyFunctionImpl;
import com.jetbrains.python.psi.types.PyType;
import com.jetbrains.python.psi.types.TypeEvalContext;
import dev.ngocta.pycharm.odoo.python.OdooPyNames;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OdooModelFunction {
    public static PyFunction wrap(@NotNull PyFunction origin, @NotNull OdooModelClassType modelClassType) {
        String name = origin.getName();
        if (name != null) {
            switch (name) {
                case PyNames.GETITEM:
                case PyNames.ITER:
                case OdooPyNames.BROWSE:
                case OdooPyNames.CREATE:
                case OdooPyNames.SUDO:
                case OdooPyNames.FILTERED:
                case OdooPyNames.SORTED:
                case OdooPyNames.SEARCH:
                case OdooPyNames.WITH_CONTEXT:
                case OdooPyNames.WITH_ENV:
                    return new Wrapper(origin, modelClassType);
            }
        }
        return origin;
    }

    public static class Wrapper extends PyFunctionImpl {
        OdooModelClassType myModelClassType;

        private Wrapper(@NotNull PyFunction origin, @NotNull OdooModelClassType modelClassType) {
            super(origin.getNode());
            myModelClassType = modelClassType;
        }

        @Nullable
        @Override
        public PyType getReturnType(@NotNull TypeEvalContext context, @NotNull TypeEvalContext.Key key) {
            return myModelClassType;
        }

        @Nullable
        @Override
        public PyType getCallType(@NotNull TypeEvalContext context, @NotNull PyCallSiteExpression callSite) {
            return myModelClassType;
        }
    }
}
