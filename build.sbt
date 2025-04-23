import Dependencies._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._
import xerial.sbt.Sonatype.sonatypeCentralHost

ThisBuild / scalaVersion           := "2.13.12"
ThisBuild / organization           := "net.jcazevedo"
ThisBuild / organizationName       := "jcazevedo"
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

lazy val root = (project in file("."))
  .settings(
    name := "sonatype-central-scala",
    developers := List(Developer("jcazevedo", "Joao Azevedo", "joao.c.azevedo@gmail.com", url("https://jcazevedo.net/"))),
    homepage := Some(url("https://github.com/jcazevedo/sonatype-central-scala")),
    licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")),
    scmInfo := Some(ScmInfo(url("https://github.com/jcazevedo/sonatype-central-scala"), "scm:git@github.com:jcazevedo/sonatype-central-scala.git")),
    publishMavenStyle := true,
    sonatypeTimeoutMillis := 60 * 60 * 1000,
    publishTo := sonatypePublishToBundle.value,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("+publishSigned"),
      releaseStepCommand("sonatypeBundleRelease"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    )
  )
