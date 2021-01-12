package com.idrica.goaigua.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.configuration.Configuration;

/**
 * Type to migrate the database. Gets initialized before hibernate.
 *
 */
public class DatabaseMigrator {

  /** Path of test data location. */
  private static final String TEST_DATA_PATH = "classpath:db/migration";

  /** Path of master data location. */
  private static final String MASTER_DATA_PATH = "classpath:db/migration";

  /** Property is true if database migration is enabled. */
  private boolean enabled;

  /** The JDBC data source. */
  private DataSource dataSource;

  /** Property is true if test data should be included in migration. */
  private boolean testdata;

  /** Property is true if database should be cleaned before migration. */
  private boolean clean;

  /**
   * Migrate the database to the latest available migration.
   */
  @SuppressWarnings("deprecation")
  public void migrate() {

    if (this.enabled) {

      Configuration configuration = new ClassicConfiguration();
      ((ClassicConfiguration) configuration).setDataSource(this.dataSource);
      if (this.testdata) {
        ((ClassicConfiguration) configuration).setLocationsAsStrings(MASTER_DATA_PATH, TEST_DATA_PATH);
      } else {
        ((ClassicConfiguration) configuration).setLocationsAsStrings(MASTER_DATA_PATH);
      }

      final Flyway flyway = new Flyway(configuration);

      if (this.clean) {
        flyway.clean();
      }
      flyway.migrate();
    }
  }

  /**
   * Import test data.
   *
   * @param importTestDataPath path to directory with files with test data
   */
  @SuppressWarnings("deprecation")
  public void importTestData(String importTestDataPath) {

    Configuration configuration = new ClassicConfiguration();
    ((ClassicConfiguration) configuration).setDataSource(this.dataSource);
    ((ClassicConfiguration) configuration).setLocationsAsStrings(importTestDataPath);

    Flyway flyway = new Flyway(configuration);
    flyway.migrate();
  }

  /**
   * @return enabled
   */
  public boolean isEnabled() {

    return this.enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {

    this.enabled = enabled;
  }

  /**
   * @return datasource
   */
  public DataSource getDataSource() {

    return this.dataSource;
  }

  /**
   * @param datasource the datasource to set
   */
  public void setDataSource(DataSource datasource) {

    this.dataSource = datasource;
  }

  /**
   * @param testdata the testdata to set
   */
  public void setTestdata(boolean testdata) {

    this.testdata = testdata;
  }

  /**
   * @param clean the clean to set
   */
  public void setClean(boolean clean) {

    this.clean = clean;
  }

}
