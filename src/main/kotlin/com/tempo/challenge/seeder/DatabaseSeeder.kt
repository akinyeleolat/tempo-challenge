package com.tempo.challenge.seeder

import com.tempo.challenge.model.Role
import com.tempo.challenge.repository.RoleRepository
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.stringtemplate.v4.ST
import java.sql.ResultSet


@Component
class DatabaseSeeder(private val roleRepository: RoleRepository, private val jdbcTemplate: JdbcTemplate) {
    @EventListener
    fun seed(event: ContextRefreshedEvent?) {
        seedRoleTable()
    }

    private fun seedRoleTable() {
        val role0 = "Developer"
        val role1 = "Product Owner"
        val role2 = "Tester"
        val query = ST(
            "SELECT name from role role WHERE name IN('<role0>','<role1>','<role2>')"
        )
        query.add("role0", role0)
        query.add("role1", role1)
        query.add("role2", role2)
        val sql = query.render()
        val rs: List<Role> = jdbcTemplate.query<Role>(sql,
            RowMapper { resultSet: ResultSet?, rowNum: Int -> null })
        if (rs.isEmpty()) {
            val defaultRole = Role(
                id=0,
                name=role0
            )
            val defaultRolePO = Role(
                id=0,
                name=role1
            )
            val defaultRoleTester = Role(
                id=0,
                name=role2
            )
            roleRepository.save<Role>(defaultRole)
            roleRepository.save<Role>(defaultRolePO)
            roleRepository.save<Role>(defaultRoleTester)
            logger.info("Role table seeded")
        } else {
            logger.trace("Role Seeding Not Required")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DatabaseSeeder::class.java)
    }
}
