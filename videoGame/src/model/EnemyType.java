package model;

public enum EnemyType {

    OGROS,
    ABSTRACTOS,
    JEFES,
    MAGICOS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
