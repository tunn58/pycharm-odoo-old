package dev.ngocta.pycharm.odoo.python.module;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.jetbrains.python.codeInsight.PyCustomMember;
import com.jetbrains.python.psi.PyFile;
import com.jetbrains.python.psi.types.PyModuleMembersProvider;
import com.jetbrains.python.psi.types.TypeEvalContext;
import dev.ngocta.pycharm.odoo.python.OdooPyNames;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OdooAddonsMembersProvider extends PyModuleMembersProvider {
    @NotNull
    @Override
    protected Collection<PyCustomMember> getMembersByQName(@NotNull PyFile file, @NotNull String name, @NotNull TypeEvalContext context) {
        Project project = file.getProject();
        if (OdooPyNames.ODOO_ADDONS.equals(name)) {
            Collection<PsiDirectory> modules = OdooModuleIndex.getAllModules(project);
            Collection<PyCustomMember> members = new ArrayList<>();
            for (PsiDirectory module : modules) {
                PyCustomMember member = new PyCustomMember(module.getName(), module);
                members.add(member);
            }
            return members;
        }
        return Collections.emptyList();
    }
}
