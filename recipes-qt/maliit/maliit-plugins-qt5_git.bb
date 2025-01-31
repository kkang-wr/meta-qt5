DESCRIPTION = "Plugins for a virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f29b21caa8e460097bfad9c026a33621"

inherit qmake5

DEPENDS = "maliit-framework-qt5"

RDEPENDS:${PN} += "qtsvg-plugins"

SRC_URI = "git://github.com/maliit/plugins.git;branch=master \
           file://0001-Do-not-use-tr1-namespace.patch \
          "

SRCREV = "c6a348592607248a771a3dde5a0e33dc3c433a2a"
PV = "0.99.0+git${SRCPV}"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${prefix} \
    MALIIT_INSTALL_PRF=${QMAKE_MKSPEC_PATH}/mkspecs/features \
    MALIIT_PLUGINS_DATA_DIR=${datadir} \
    LIBDIR=${libdir} \
    CONFIG+=nodoc \
"

# tests fail to build with clang
EXTRA_QMAKEVARS_PRE:append:toolchain-clang = " CONFIG+=notests"

FILES:${PN} += "\
    ${libdir}/maliit \
    ${datadir} \
"

S= "${WORKDIR}/git"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
