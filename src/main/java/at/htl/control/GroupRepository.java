package at.htl.control;

import at.htl.entity.GroupGP;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupRepository  implements PanacheRepository<GroupGP> {
}
