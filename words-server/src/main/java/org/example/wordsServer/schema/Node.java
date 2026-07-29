package org.example.wordsServer.schema;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

@Entity
@Table(name = "nodes")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Node {
    public static @Nullable Long getChildNodeId(@NonNull Node node, @NonNull Character toSearch) {
        return switch(toSearch) {
            case 'a' -> node.getNextIda();
            case 'b' -> node.getNextIdb();
            case 'c' -> node.getNextIdc();
            case 'd' -> node.getNextIdd();
            case 'e' -> node.getNextIde();
            case 'f' -> node.getNextIdf();
            case 'g' -> node.getNextIdg();
            case 'h' -> node.getNextIdh();
            case 'i' -> node.getNextIdi();
            case 'j' -> node.getNextIdj();
            case 'k' -> node.getNextIdk();
            case 'l' -> node.getNextIdl();
            case 'm' -> node.getNextIdm();
            case 'n' -> node.getNextIdn();
            case 'o' -> node.getNextIdo();
            case 'p' -> node.getNextIdp();
            case 'q' -> node.getNextIdq();
            case 'r' -> node.getNextIdr();
            case 's' -> node.getNextIds();
            case 't' -> node.getNextIdt();
            case 'u' -> node.getNextIdu();
            case 'v' -> node.getNextIdv();
            case 'w' -> node.getNextIdw();
            case 'x' -> node.getNextIdx();
            case 'y' -> node.getNextIdy();
            case 'z' -> node.getNextIdz();
            case '-' -> node.getNextIdDash();
            default -> null;
        };
    }

    public static void setChildNodeId(@NonNull Node node, @NonNull Character childChar, Long childNodeId) {
        switch(childChar) {
            case 'a' -> node.setNextIda(childNodeId);
            case 'b' -> node.setNextIdb(childNodeId);
            case 'c' -> node.setNextIdc(childNodeId);
            case 'd' -> node.setNextIdd(childNodeId);
            case 'e' -> node.setNextIde(childNodeId);
            case 'f' -> node.setNextIdf(childNodeId);
            case 'g' -> node.setNextIdg(childNodeId);
            case 'h' -> node.setNextIdh(childNodeId);
            case 'i' -> node.setNextIdi(childNodeId);
            case 'j' -> node.setNextIdj(childNodeId);
            case 'k' -> node.setNextIdk(childNodeId);
            case 'l' -> node.setNextIdl(childNodeId);
            case 'm' -> node.setNextIdm(childNodeId);
            case 'n' -> node.setNextIdn(childNodeId);
            case 'o' -> node.setNextIdo(childNodeId);
            case 'p' -> node.setNextIdp(childNodeId);
            case 'q' -> node.setNextIdq(childNodeId);
            case 'r' -> node.setNextIdr(childNodeId);
            case 's' -> node.setNextIds(childNodeId);
            case 't' -> node.setNextIdt(childNodeId);
            case 'u' -> node.setNextIdu(childNodeId);
            case 'v' -> node.setNextIdv(childNodeId);
            case 'w' -> node.setNextIdw(childNodeId);
            case 'x' -> node.setNextIdx(childNodeId);
            case 'y' -> node.setNextIdy(childNodeId);
            case 'z' -> node.setNextIdz(childNodeId);
            case '-' -> node.setNextIdDash(childNodeId);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "value")
    @NonNull
    private Character character;

    @Column(nullable = false)
    @NonNull
    private Boolean isRoot;

    @Column(nullable = false)
    @NonNull
    private Boolean isTerminal;

    private Long count;

    @Column(name = "id_a")
    private Long nextIda;

    @Column(name = "id_b")
    private Long nextIdb;

    @Column(name = "id_c")
    private Long nextIdc;

    @Column(name = "id_d")
    private Long nextIdd;

    @Column(name = "id_e")
    private Long nextIde;

    @Column(name = "id_f")
    private Long nextIdf;

    @Column(name = "id_g")
    private Long nextIdg;

    @Column(name = "id_h")
    private Long nextIdh;

    @Column(name = "id_i")
    private Long nextIdi;

    @Column(name = "id_j")
    private Long nextIdj;

    @Column(name = "id_k")
    private Long nextIdk;

    @Column(name = "id_l")
    private Long nextIdl;

    @Column(name = "id_m")
    private Long nextIdm;

    @Column(name = "id_n")
    private Long nextIdn;

    @Column(name = "id_o")
    private Long nextIdo;

    @Column(name = "id_p")
    private Long nextIdp;

    @Column(name = "id_q")
    private Long nextIdq;

    @Column(name = "id_r")
    private Long nextIdr;

    @Column(name = "id_s")
    private Long nextIds;

    @Column(name = "id_t")
    private Long nextIdt;

    @Column(name = "id_u")
    private Long nextIdu;

    @Column(name = "id_v")
    private Long nextIdv;

    @Column(name = "id_w")
    private Long nextIdw;

    @Column(name = "id_x")
    private Long nextIdx;

    @Column(name = "id_y")
    private Long nextIdy;

    @Column(name = "id_z")
    private Long nextIdz;

    @Column(name = "id_dash")
    private Long nextIdDash;



}
