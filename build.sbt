import Dependencies._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

ThisBuild / scalaVersion           := "2.13.12"
ThisBuild / organization           := "net.jcazevedo"
ThisBuild / organizationName       := "jcazevedo"

lazy val root = (project in file("."))
  .settings(
    name := "sonatype-central-scala",
    developers := List(Developer("jcazevedo", "Joao Azevedo", "joao.c.azevedo@gmail.com", url("https://jcazevedo.net/"))),
    homepage := Some(url("https://github.com/jcazevedo/sonatype-central-scala")),
    licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")),
    scmInfo := Some(ScmInfo(url("https://github.com/jcazevedo/sonatype-central-scala"), "scm:git@github.com:jcazevedo/sonatype-central-scala.git")),
    publishMavenStyle := true,
    publishTo := {
      val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
      if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
      else localStaging.value
    },
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("+publishSigned"),
      releaseStepCommand("sonaRelease"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    )
  )
