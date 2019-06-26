package repository.subjects

import domain.subjects.UniversityCourses
import repository.Repository
import repository.subjects.Impl.UniversityCoursesRepositoryImpl

trait UniversityCoursesRepository extends Repository [UniversityCourses]{

}
object UniversityCoursesRepository{
  def apply: UniversityCoursesRepositoryImpl = new UniversityCoursesRepositoryImpl()
}