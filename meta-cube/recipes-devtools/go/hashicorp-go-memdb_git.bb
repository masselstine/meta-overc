SUMMARY = "Golang in-memory database built on immutable radix trees"
HOMEPAGE = "https://github.com/hashicorp/go-memdb"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

PKG_NAME = "github.com/hashicorp/go-memdb"
SRC_URI = "git://${PKG_NAME}.git"
SRCREV = "98f52f52d7a476958fa9da671354d270c50661a7"

S = "${WORKDIR}/git"

DEPENDS += " hashicorp-go-immutable-radix"

do_install() {
    install -d ${D}${prefix}/local/go/src/${PKG_NAME}
    cp -a ${S}/* ${D}${prefix}/local/go/src/${PKG_NAME}/
}

SYSROOT_PREPROCESS_FUNCS += "hashicorp_memdb_sysroot_preprocess"

hashicorp_memdb_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${prefix}/local/go/src/${PKG_NAME}
    cp -a ${D}${prefix}/local/go/src/${PKG_NAME} ${SYSROOT_DESTDIR}${prefix}/local/go/src/$(dirname ${PKG_NAME})
}

FILES_${PN} += "${prefix}/local/go/src/${PKG_NAME}/*"