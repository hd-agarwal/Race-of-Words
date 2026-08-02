USE words;
CREATE TABLE IF NOT EXISTS nodes
(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `value` varchar(1) NOT NULL,
  `count` bigint DEFAULT NULL,
  `is_root` bit(1) NOT NULL,
  `is_terminal` bit(1) NOT NULL,
  `id_dash` bigint DEFAULT NULL,
  `id_a` bigint DEFAULT NULL,
  `id_b` bigint DEFAULT NULL,
  `id_c` bigint DEFAULT NULL,
  `id_d` bigint DEFAULT NULL,
  `id_e` bigint DEFAULT NULL,
  `id_f` bigint DEFAULT NULL,
  `id_g` bigint DEFAULT NULL,
  `id_h` bigint DEFAULT NULL,
  `id_i` bigint DEFAULT NULL,
  `id_j` bigint DEFAULT NULL,
  `id_k` bigint DEFAULT NULL,
  `id_l` bigint DEFAULT NULL,
  `id_m` bigint DEFAULT NULL,
  `id_n` bigint DEFAULT NULL,
  `id_o` bigint DEFAULT NULL,
  `id_p` bigint DEFAULT NULL,
  `id_q` bigint DEFAULT NULL,
  `id_r` bigint DEFAULT NULL,
  `id_s` bigint DEFAULT NULL,
  `id_t` bigint DEFAULT NULL,
  `id_u` bigint DEFAULT NULL,
  `id_v` bigint DEFAULT NULL,
  `id_w` bigint DEFAULT NULL,
  `id_x` bigint DEFAULT NULL,
  `id_y` bigint DEFAULT NULL,
  `id_z` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO nodes (value, is_root, is_terminal)
SELECT '\0', true, false
FROM DUAL
WHERE (SELECT COUNT(*) FROM nodes) = 0; 