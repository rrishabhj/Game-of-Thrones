package es.npatarino.android.gotchallenge.characters.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio López.
 */
@Qualifier
@Retention(RUNTIME)
public @interface Character {}