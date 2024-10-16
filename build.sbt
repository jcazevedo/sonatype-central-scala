import Dependencies._
import xerial.sbt.Sonatype.sonatypeCentralHost

ThisBuild / scalaVersion           := "2.13.12"
ThisBuild / organization           := "net.jcazevedo"
ThisBuild / organizationName       := "jcazevedo"
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost
ThisBuild / sonatypeRepository     := "https://s01.oss.sonatype.org/service/local"

lazy val root = (project in file("."))
  .settings(
    name := "sonatype-central-scala",
    libraryDependencies += munit % Test,
    developers := List(Developer("jcazevedo", "Joao Azevedo", "joao.c.azevedo@gmail.com", url("https://jcazevedo.net/"))),
    homepage := Some(url("https://github.com/jcazevedo/sonatype-central-scala")),
    licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")),
    scmInfo := Some(ScmInfo(url("https://github.com/jcazevedo/sonatype-central-scala"), "scm:git@github.com:jcazevedo/sonatype-central-scala.git")),
    publishMavenStyle := true,
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

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
