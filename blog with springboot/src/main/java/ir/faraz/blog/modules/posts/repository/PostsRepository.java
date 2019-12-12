package ir.faraz.blog.modules.posts.repository;

import ir.faraz.blog.modules.posts.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {


    //this command is not good because we cant retrieve all posts with null model attribute and we should write query
    //List<Posts> findByTitleContaining(String title);

    /// So we use this command to search by title and category
    //    @Query("select p from Posts p where :#{#posts.title} is null or p.title like concat('%',:#{#posts.title},'%')")
    /*@Query("select p from Posts p join p.categories pc where (:#{#posts.title} is null or " +
            "p.title like concat('%',:#{#posts.title},'%')) and " +
            "(coalesce(:#{#posts.categories},null) is null or " +
            "pc in (:#{#posts.categories})) " +
            "group by p.id having count (p.id) >= :num")
    Page<Posts> findBySearch(Posts posts, @Param("num") Long size, Pageable pageable);
*/

    @Query("select p from Posts p join p.categories pc where (:#{#posts.title} is null or " +
            "p.title like concat('%',:#{#posts.title},'%')) and " +
            "(coalesce(:#{#posts.categories},null) is null or " +
            "pc in (:#{#posts.categories})) " +
            "group by p.id having count (p.id) >= :num")
    List<Posts> findBySearch(Posts posts,@Param("num") Long size);
}