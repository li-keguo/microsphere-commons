package io.microsphere.classloading;

import io.microsphere.annotation.Nonnull;
import io.microsphere.annotation.Nullable;
import io.microsphere.constants.SymbolConstants;

import java.net.URL;
import java.util.Objects;
import java.util.function.Function;

import static io.microsphere.constants.SymbolConstants.QUOTE_CHAR;
import static java.util.Objects.hash;

/**
 * Artifact entity
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @since 1.0.0
 */
public class Artifact {

    public static final String WILDCARD = SymbolConstants.WILDCARD;

    public static final String UNKNOWN = SymbolConstants.QUESTION_MARK;

    @Nonnull
    private final String artifactId;

    @Nullable
    private final String version;

    @Nullable
    private final URL location;

    public Artifact(@Nonnull String artifactId, @Nullable String version, @Nullable URL location) {
        this.artifactId = artifactId;
        this.version = version;
        this.location = location;
    }

    public static Artifact create(@Nonnull String artifactId, @Nullable String version, @Nullable URL location) {
        return new Artifact(artifactId, version, location);
    }

    public static Artifact create(@Nonnull String artifactId, @Nullable String version) {
        return create(artifactId, version, null);
    }

    public static Artifact create(@Nonnull String artifactId) {
        return create(artifactId, UNKNOWN);
    }

    /**
     * Get the artifact Id
     *
     * @return non-null
     */
    @Nonnull
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Get the version
     *
     * @return nullable
     */
    @Nullable
    public String getVersion() {
        return version;
    }

    /**
     * Get the location of artifact resource
     *
     * @return nullable
     */
    @Nullable
    public URL getLocation() {
        return location;
    }

    public boolean matches(Artifact artifact) {
        return matchesArtifactId(artifact)
                && matchesVersion(artifact);
    }

    protected boolean matchesArtifactId(Artifact artifact) {
        return matches(artifact, Artifact::getArtifactId);
    }

    protected boolean matchesVersion(Artifact artifact) {
        return matches(artifact, Artifact::getVersion);
    }

    protected boolean matches(Artifact artifact, Function<Artifact, String> getterFunction) {
        String configuredValue = getterFunction.apply(this);
        if (WILDCARD.equals(configuredValue)) {
            return true;
        }
        String value = getterFunction.apply(artifact);
        return configuredValue.equals(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artifact)) return false;
        Artifact that = (Artifact) o;
        return Objects.equals(artifactId, that.artifactId) &&
                Objects.equals(version, that.version) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return hash(artifactId, version, location);
    }

    @Override
    public String toString() {
        String sb = "Artifact{" + "artifactId='" + artifactId + QUOTE_CHAR +
                ", version='" + version + QUOTE_CHAR +
                ", location='" + location + QUOTE_CHAR +
                '}';
        return sb;
    }
}
