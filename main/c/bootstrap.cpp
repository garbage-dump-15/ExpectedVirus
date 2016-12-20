#include <windows.h>

int main(int argc,char ** argv){
    WinExec("copy splash.src net\\splash.png",SW_HIDE);
    WinExec("javaw -splash:net\\splash.png net.steepout.music.badwin.Main",SW_HIDE);
    WinExec("del /f net\\splash.png",SW_HIDE);
    /*
    
    */
    return 0;
}

