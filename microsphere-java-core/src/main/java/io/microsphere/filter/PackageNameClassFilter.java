/**
 *
 */
package io.microsphere.filter;

import static io.microsphere.constants.SymbolConstants.DOT;

/**
 * {@link PackageNameClassFilter}
 *
 * @author <a href="mercyblitz@gmail.com">Mercy<a/>
 * @see PackageNameClassFilter
 * @since 1.0.0
 */
public class PackageNameClassFilter implements ClassFilter {

    private final String packageName;
    private final boolean includedSubPackages;
    private final String subPackageNamePrefix;

    /**
     * Constructor
     *
     * @param packageName         the name of package
     * @param includedSubPackages included sub-packages
     */
    public PackageNameClassFilter(String packageName, boolean includedSubPackages) {
        this.packageName = packageName;
        this.includedSubPackages = includedSubPackages;
        this.subPackageNamePrefix = includedSubPackages ? packageName + DOT : null;
    }

    @Override
    public boolean accept(Class<?> filteredObject) {
        if (filteredObject == null) {
            return false;
        }
        Package package_ = filteredObject.getPackage();
        String packageName = package_.getName();
        boolean accepted = packageName.equals(this.packageName);
        if (!accepted && includedSubPackages) {
            accepted = packageName.startsWith(subPackageNamePrefix);
        }
        return accepted;
    }
}
