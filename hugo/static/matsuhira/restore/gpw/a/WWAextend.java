// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi 

import java.applet.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.StringTokenizer;

public class WWAextend extends Applet
    implements Runnable
{

    public WWAextend()
    {
        a = true;
        bg = 1;
        bm = new MediaTracker(this);
        bq = 4;
        br = new AudioClip[100];
        bs = false;
        bM = false;
        bQ = false;
        bR = false;
        bV = false;
        bW = false;
        bX = true;
        bY = true;
        bZ = false;
        ca = false;
        cb = false;
        ch = true;
        cl = 0;
        cm = 10;
        cn = 0;
        cs = new boolean[11][11];
        cy = -1;
        cz = 0;
        cD = 101;
        cR = false;
        cS = true;
        cT = false;
        cU = new int[13][13][2];
        cV = 0;
        cW = false;
        cX = 0;
        cZ = new byte[65000];
        da = new int[200];
        dg = 0;
        dj = 1;
        dk = 1;
        dl = 0;
        _flddo = 0;
        dq = new Random();
        dr = 0;
        ds = new int[4];
        dt = 0;
        dw = 0;
        dz = 0;
        dA = new int[4];
        dB = false;
        eG = "　【ショートカットキーの一覧】\nＦ１、Ｍ：戦闘結果予測の表示\nＦ２、Ｐ：移動速度の切り換え\nＦ３：復帰用パスワード入力\nＦ４：復帰用パスワード表示\nＦ５：一時保存データの読み込み\nＦ６：データの一時保存\nＦ７：初めからスタート\nＦ８：ＷＷＡ公式ページにリンク\nＦ９、Ｇ：描画モードの切り換え\nＦ１２：このリストの表示\nＬ：リンクを別のウィンドウで開く\nキーボードの「１２３、ＱＷＥ、\nＡＳＤ、ＺＸＣ」は\n右のアイテムボックスに対応。\n「Ｅｎｔｅｒ、Ｙ」はＹｅｓ\n「Ｅｓｃ、Ｎ」はＮｏに対応。\n　　現在の移動回数：";
        eN = new Checkbox("このウィンドウを閉じるにはここを押してください。");
        eO = new Checkbox("データを入力したらここを押してください。");
        eP = false;
        eQ = false;
        eR = new TextArea();
    }

    public void init()
    {
        bs = false;
        cO = false;
        bN = false;
        bO = false;
        ch = true;
        bH = 0;
        bI = 0;
        cu = false;
        ep = false;
        eq = false;
        cc = false;
        cd = false;
        ce = false;
        cf = false;
        ck = 0;
        cw = 0;
        cx = false;
        cg = 0;
        ct = true;
        bS = false;
        bT = false;
        bU = false;
        df = null;
        ci = null;
        bt = createImage(440, 440);
        bA = bt.getGraphics();
        bv = createImage(120, 440);
        bC = bv.getGraphics();
        bu = createImage(40, 40);
        bB = bu.getGraphics();
        bw = createImage(80, 40);
        bD = bw.getGraphics();
        bx = createImage(40, 80);
        bE = bx.getGraphics();
        by = createImage(340, 60);
        bF = by.getGraphics();
        bz = createImage(120, 35);
        bG = bz.getGraphics();
        e();
        System.out.println("<init>");
    }

    public void start()
    {
        if(ci == null)
        {
            ci = new Thread(this);
            ci.start();
        }
        System.out.println("<start>");
    }

    public void stop()
    {
        System.out.println("<stop>");
    }

    public void destroy()
    {
        a(99);
        if(ci != null)
            ci.stop();
        bA.dispose();
        bC.dispose();
        bB.dispose();
        bD.dispose();
        bE.dispose();
        bF.dispose();
        for(int i1 = 0; i1 < cE; i1++)
            bo[i1].flush();

        System.gc();
        System.out.println("<destroy>");
    }

    public void run()
    {
        do
        {
            if(!ci.isAlive())
                break;
            try
            {
                if(bO)
                    Thread.sleep(500L);
                if(bH == 8 || !bM)
                    Thread.sleep(200L);
                else
                    Thread.sleep(el);
            }
            catch(InterruptedException interruptedexception)
            {
                System.err.println("111 Thread Error!");
            }
            if(!cx)
                repaint();
        } while(true);
    }

    public void update(Graphics g1)
    {
        paint(g1);
    }

    public void paint(Graphics g1)
    {
        if(b(g1))
            return;
        if(!bM)
        {
            a(g1, 0);
            a(g1, getParameter("paramMapName"), true);
            if(bS || bV)
                return;
            a(g1, 1);
            h(g1);
            if(bT)
                return;
            e(500);
            bH = 8;
            bL = true;
            bM = true;
        }
        if(eN.getState() || eO.getState())
        {
            if(eQ)
                l();
            eM.dispose();
            eN.setState(false);
            eO.setState(false);
            eP = false;
            bO = false;
            ct = true;
            cu = false;
            cn = 3;
        }
        if(bR)
            k(g1);
        if(!ch)
        {
            cl = 200;
            g1.drawImage(bt, 0, 0, this);
            g1.drawImage(bv, 440, 0, this);
            return;
        }
        if(!c(g1))
            return;
        if(bL)
            return;
        if(cv)
        {
            a(g1, true, true, true, true);
            cv = false;
        }
        if(cO)
        {
            b(g1, cM, cN);
            return;
        }
        if(!ct && ch && !cO)
            if(cw != 0)
                cw--;
            else
                g(g1);
        if(eF)
            j(g1);
        if(es)
            a(dO, dP);
        if(eD)
            a(eo);
        dO = be / 5 + dW * 10;
        dP = bf / 5 + dX * 10;
        if(cn > 0)
        {
            ct = true;
            cu = false;
            cn--;
        }
        if(ct)
        {
            cx = true;
            a(g1, true);
            cx = false;
            cw = 3;
        }
        if(er)
        {
            er = false;
            a(g1, true);
        }
        if(eq)
        {
            a(g1, true, true, true, true);
            f(0);
            eq = false;
        }
        e(g1);
        b();
        dB = false;
        if(cl != 0)
        {
            e(cl);
            cl = 0;
        }
        if(!ep && eB)
        {
            ep = true;
            eB = false;
            dT = 1;
        }
        if(ep)
            a(g1);
        if(bN)
        {
            i(bA);
            bN = false;
        }
        if(cz > 0 && ch)
        {
            cz = 0;
            cy = -1;
            e(100);
            a(g1, true, false, true, false);
        }
        for(int i1 = 0; i1 < 4; i1++)
        {
            if(dA[i1] == 1 && ex == 1)
                eq = true;
            if(dA[i1] > 0)
                dA[i1]--;
        }

    }

    public void a(Graphics g1)
    {
        ep = false;
        if(_flddo == -1)
            _flddo = 0;
        if(!dR[dT].equals("") && dT != 0)
        {
            byte byte0;
            if(bN)
                byte0 = 80;
            else
            if(bZ)
                byte0 = -3;
            else
            if(bf / 5 >= 6)
                byte0 = -2;
            else
                byte0 = -1;
            if(!a(bA, g1, dR[dT], 50, byte0))
                return;
            ch = false;
            ct = true;
            cu = false;
            cw = 3;
        }
        bZ = false;
    }

    public void a(Graphics g1, int i1)
    {
        g1.setColor(Color.white);
        g1.fillRect(0, 0, 560, 440);
        g1.setColor(Color.black);
        Font font = new Font("TimesRoman", 0, 32);
        g1.setFont(font);
        g1.drawString("Welcome to MSP!", 100, 70);
        font = new Font("TimesRoman", 0, 18);
        g1.setFont(font);
        g1.drawString("(C)B.C.2000-A.D.2935  Masapy   Ver 9.80", 160, 390);
        if(a)
        {
            a(4, 0, 0, 0, 0, 0, null, null, null, 0, 0);
            g1.drawString("Customized by " + eu, 160, 420);
        }
        font = new Font("TimesRoman", 0, 22);
        g1.setFont(font);
        if(i1 >= 0)
            g1.drawString("マップデータロード中 .....", 50, 140);
        if(i1 >= 1)
        {
            g1.drawString("マップデータロード中 ..... OK!", 50, 140);
            g1.drawString("画像データロード中 .....", 50, 170);
        }
        if(i1 >= 3)
        {
            g1.drawString("画像データロード中 ..... OK!", 50, 170);
            g1.drawString("キャラクタ画像生成中 .....", 50, 200);
        }
        if(i1 >= 4)
            g1.drawString("キャラクタ画像生成中 ..... OK!", 50, 200);
        if(i1 >= 1)
        {
            Font font1 = new Font("TimesRoman", 0, 18);
            g1.setFont(font1);
            g1.drawString("World Name   " + bl, 160, 360);
        }
    }

    public int a(String s1)
    {
        int i1 = 0;
        try
        {
            i1 = Integer.parseInt(s1);
        }
        catch(NumberFormatException numberformatexception) { }
        return i1;
    }

    public boolean b(Graphics g1)
    {
        if(bS || bT || bU || bV || cb)
        {
            g1.setColor(Color.white);
            g1.fillRect(0, 0, 560, 440);
            g1.setColor(Color.black);
            Font font = new Font("TimesRoman", 0, 16);
            g1.setFont(font);
            if(bU)
            {
                g1.drawString("マップデータが壊れています。", 10, 180);
                g1.drawString("テキストモードで送信していないか確認してください。", 10, 210);
            } else
            if(bS)
            {
                g1.drawString("マップデータファイル " + getParameter("paramMapName") + " が見つかりません。", 10, 180);
                g1.drawString("データがアップロードされているか確認してください。", 10, 210);
            } else
            if(bT)
            {
                if(bh.equals(""))
                {
                    g1.drawString("画像データファイル名が見つかりません。", 10, 180);
                    g1.drawString("マップデータが壊れています。", 10, 210);
                } else
                {
                    g1.drawString("画像データファイル " + bh + " が見つからないかアクセスできません。", 10, 180);
                    g1.drawString("データがアップロードされているか、", 10, 210);
                    g1.drawString("パーミッションが読み込み可になっているかなどを確認してください。", 10, 240);
                }
            } else
            if(bV)
            {
                g1.drawString("マップデータにアクセスできません。", 10, 180);
                g1.drawString("パーミッションが読み込み可になっているかなどを確認してください。", 10, 210);
            } else
            if(cb)
                g1.drawString("古いマップや暗証番号が４桁以上のマップには拡張マクロは使えません。", 10, 180);
            e(1000);
            return true;
        } else
        {
            return false;
        }
    }

    public void a(Graphics g1, Graphics g2, String s1, int i1, int j1, int k1)
    {
        if(j1 == 0)
            j1 = -3;
        if(bL)
        {
            bJ = k1;
            if(bH == 8)
            {
                bA.setColor(Color.white);
                bA.fillRect(0, 0, 440, 440);
                bC.setColor(Color.white);
                bC.fillRect(0, 0, 120, 440);
                a(bA, ((Graphics) (null)), s1, i1, j1);
            } else
            {
                a(g1, g2, s1, i1, j1);
            }
            bK = dh;
            bL = false;
        }
    }

    public void a(int i1)
    {
        if(eo == i1)
            return;
        if(i1 == 99 && eo != 0 && br[eo] != null && bs)
        {
            br[eo].stop();
            eo = 0;
            return;
        }
        if(i1 == 100 && eo != 0 && br[eo] != null && bs)
            br[eo].loop();
        if(i1 != 0 && i1 < 100 && br[i1] != null && bs)
            if(i1 < 70)
            {
                br[i1].play();
            } else
            {
                if(eo != 0 && br[eo] != null)
                    br[eo].stop();
                eo = i1;
                br[eo].loop();
            }
    }

    public void b(int i1)
    {
        if(i1 < 100)
            try
            {
                br[i1] = getAudioClip(getDocumentBase(), i1 + ".au");
            }
            catch(Exception exception)
            {
                System.err.println("150 Audio Error!");
                bW = true;
            }
    }

    public void a()
    {
        b("セーブなんて甘いこと\n許されません。", true);
        bH = 0;
        bI = 0;
        bL = false;
    }

    public boolean c(Graphics g1)
    {
        byte byte0;
        if(bf / 5 >= 6)
            byte0 = -2;
        else
            byte0 = -1;
        if(bH == 9 && dR[8].equals("BLANK"))
        {
            d(g1);
            bH = 0;
            bL = false;
        }
        if(bH != 0)
        {
            if(!bL)
            {
                bB.setColor(Color.white);
                bB.fillRect(0, 0, 40, 40);
                if(bI == 1)
                {
                    bB.drawImage(bo[dE], 0, 0, this);
                    bA.drawImage(bu, bJ + 2, bK + 2, this);
                }
                bB.setColor(Color.white);
                bB.fillRect(0, 0, 40, 40);
                if(bI == 2)
                {
                    bB.drawImage(bo[dF], 0, 0, this);
                    bA.drawImage(bu, bJ + 2 + 40, bK + 2, this);
                }
                g1.drawImage(bt, 0, 0, this);
                g1.drawImage(bv, 440, 0, this);
                e(100);
                if(bI == 1 || bI == 2)
                    a(1);
            }
            if(bH == 3)
            {
                a(bA, g1, "ＭＳＰの公式サイトを開きますか？", 50, 0, 296);
                if(bI == 1)
                    try
                    {
                        bO = true;
                        URL url = new URL("http://www.geocities.jp/m10oekaki/");
                        if(cW)
                            getAppletContext().showDocument(url, "new");
                        else
                            getAppletContext().showDocument(url);
                    }
                    catch(MalformedURLException malformedurlexception)
                    {
                        System.err.println("141 URL Error!");
                    }
            }
            if(bH == 4)
            {
                if(dR[5].equals(""))
                    a(bA, g1, "他のページにリンクします。\nよろしいですか？", 50, 0, 296);
                else
                    a(bA, g1, dR[5], 50, 0, 296);
                if(bI == 1)
                    a(dR[dT], false);
            }
            if(bH == 5)
            {
                a(bA, g1, "白紙からゲームを始めますか？", 50, 0, 296);
                if(bI == 1)
                {
                    a(g1, "", false);
                    dU = 2;
                    c(2);
                    g(13);
                    ev = 0;
                    ew = 0;
                    dg = 0;
                    en = 0;
                    dr = 0;
                    dV = 0;
                    ex = 1;
                    cR = true;
                    ep = false;
                    _flddo = 0;
                    a(99);
                    cS = true;
                }
            }
            if(bH == 6)
            {
                a(bA, g1, dR[dT], 50, byte0, 296);
                if(bI == 1)
                {
                    int i3 = ea[cp][14];
                    int i1;
                    for(i1 = 0; i1 < 12 && eh[i1] != 0; i1++);
                    int l2 = ea[i3][15];
                    if(i1 == 12 && i3 != 0 && (l2 == 0 || ea[eh[l2 - 1]][15] == 0))
                    {
                        ep = true;
                        dT = 1;
                        if(dS[1].equals(""))
                            dR[dT] = "これ以上、アイテムを持てません。";
                        else
                            dR[dT] = dS[1];
                    } else
                    if(eg >= ea[cp][13])
                    {
                        eg -= ea[cp][13];
                        if(ea[cp][10] > 30000)
                        {
                            ec -= ea[cp][10] - 30000;
                            if(ec <= 0 && ea[cp][10] != 0)
                            {
                                j(g1);
                                bH = 0;
                                bI = 0;
                                return false;
                            }
                        } else
                        {
                            ec += ea[cp][10];
                        }
                        ee += ea[cp][11];
                        ef += ea[cp][12];
                        if(ea[cp][10] != 0)
                            dA[0] = 20;
                        if(ea[cp][11] != 0)
                            dA[1] = 20;
                        if(ea[cp][12] != 0)
                            dA[2] = 20;
                        if(ea[cp][13] != 0)
                            dA[3] = 20;
                        if(ea[ea[cp][14]][11] != 0)
                            dA[1] = 20;
                        if(ea[ea[cp][14]][12] != 0)
                            dA[2] = 20;
                        if(i3 != 0)
                            a(i1, l2, cq, cr);
                        f(ea[cp][14]);
                        a(g1, true, true, true, false);
                        ct = true;
                        b(g1, cq, cr, 0, cp);
                    } else
                    if(dR[6].equals(""))
                    {
                        ep = true;
                        dT = 1;
                        dR[dT] = "所持金がたりない。";
                    } else
                    if(!dR[6].equals("BLANK"))
                    {
                        ep = true;
                        dT = 6;
                    }
                }
            }
            if(bH == 7)
            {
                a(bA, g1, dR[dT], 50, byte0, 296);
                if(bI == 1)
                {
                    int j1 = 0;
                    do
                    {
                        if(j1 >= 12)
                            break;
                        if(ea[cp][14] == eh[j1])
                        {
                            eg += ea[cp][13];
                            if(ea[cp][13] != 0)
                                dA[3] = 20;
                            eh[j1] = 0;
                            f(0);
                            a(g1, true, true, true, false);
                            ct = true;
                            b(g1, cq, cr, 0, cp);
                            break;
                        }
                        j1++;
                    } while(true);
                    if(j1 == 12)
                        if(dR[7].equals(""))
                        {
                            ep = true;
                            dT = 1;
                            dR[dT] = "アイテムを持っていない。";
                        } else
                        if(!dR[7].equals("BLANK"))
                        {
                            ep = true;
                            dT = 7;
                        }
                }
            }
            if(bH == 12)
            {
                a(bA, g1, dR[dT], 50, byte0, 296);
                if(bI == 1)
                {
                    ct = true;
                    b(g1, cq, cr, 3, cp);
                } else
                if(bI == 2)
                {
                    ct = true;
                    b(g1, cq, cr, 4, cp);
                }
            }
            if(bH == 8)
            {
                char c1 = '\u0168';
                if(dS[2].equals(""))
                    a(bA, g1, "効果音データをロードしますか？", 50, 0, 296);
                else
                if(dS[2].equals("ON") || dS[2].equals("on"))
                {
                    bI = 1;
                    c1 = '\346';
                    b("ゲームを開始します。\n画面をクリックしてください。", true);
                } else
                if(dS[2].equals("OFF") || dS[2].equals("off"))
                {
                    bI = 2;
                    b("ゲームを開始します。\n画面をクリックしてください。", true);
                } else
                {
                    a(bA, g1, dS[2], 50, 0, 296);
                }
                bL = false;
                if(bI == 1)
                {
                    g1.setColor(Color.black);
                    Font font = new Font("TimesRoman", 0, 22);
                    g1.setFont(font);
                    g1.drawString("Now Sound data Loading .....", 50, c1);
                    for(int i2 = 1; i2 <= 3; i2++)
                        if(i2 != 2)
                            b(i2);

                    for(int k1 = 0; k1 < cF; k1++)
                    {
                        int j2 = eb[k1][19];
                        if(j2 != 0 && j2 < 100 && br[j2] == null)
                            b(j2);
                    }

                    for(int l1 = 0; l1 < cG; l1++)
                    {
                        int k2 = ea[l1][19];
                        if(k2 != 0 && k2 < 100 && br[k2] == null && ea[l1][3] != 16)
                            b(k2);
                    }

                    bs = true;
                    g1.drawString("音楽データロード中 ..... OK!", 50, c1);
                    e(500);
                } else
                if(bI == 2)
                    bs = false;
            }
            if(bH == 9)
            {
                if(be % 5 == 0 && bf % 5 == 0)
                {
                    if(dR[8].equals(""))
                        a(bA, g1, "このアイテムを使います。\nよろしいですか？", 50, byte0, 296);
                    else
                        a(bA, g1, dR[8], 50, byte0, 296);
                } else
                {
                    bH = 0;
                    bI = 0;
                    bL = false;
                }
                if(bI == 1)
                    d(g1);
            }
            if(bH == 10)
            {
                if(ev == 1)
                {
                    a();
                    return true;
                }
                a(bA, g1, "データの一時保存をします。\nよろしいですか？\n→Ｎｏでデータ復帰用パスワードの\n　表示選択ができます。", 50, 0, 296);
                if(bI == 1)
                {
                    f();
                    bQ = true;
                } else
                if(bI == 2)
                {
                    bH = 13;
                    bI = 0;
                    bL = true;
                    e(200);
                }
            }
            if(bH == 11)
            {
                a(bA, g1, "データを読み込みますか？\n→Ｎｏでデータ復帰用パスワードの\n　入力選択ができます。", 50, 0, 296);
                if(bI == 1)
                    g();
                else
                if(bI == 2)
                {
                    bH = 14;
                    bI = 0;
                    bL = true;
                    e(200);
                }
            }
            if(bH == 13)
            {
                if(ev == 1)
                {
                    a();
                    return true;
                }
                a(bA, g1, "無限に続くパスワードを\n表示しますか？", 50, 0, 296);
                if(bI == 1)
                    k();
            }
            if(bH == 14)
            {
                a(bA, g1, "無限に続くパスワードを\n入力しますか？", 50, 0, 296);
                if(bI == 1)
                    i();
            }
            if(bI != 0)
            {
                bH = 0;
                bI = 0;
                ct = true;
                cu = false;
                e(200);
            }
            return false;
        } else
        {
            return true;
        }
    }

    public void d(Graphics g1)
    {
        ep = true;
        dT = ea[eh[cy]][5];
        ct = true;
        b(g1, dO, dP, 2);
        if(ea[eh[cy]][4] == 1)
            eh[cy] = 0;
    }

    public void a(Graphics g1, boolean flag)
    {
        ct = false;
        for(int k1 = 0; k1 < 13; k1++)
        {
            for(int i1 = 0; i1 < 13; i1++)
            {
                for(int i2 = 0; i2 < 2; i2++)
                    cU[k1][i1][i2] = 0;

            }

        }

        if(cR)
        {
            cR = false;
            g1.setColor(Color.gray);
            for(int j2 = 0; j2 < 220; j2++)
            {
                g1.drawRect(j2, j2, 440 - j2 * 2, 440 - j2 * 2);
                if(j2 % 10 == 0)
                    e(20);
            }

            e(20);
            g1.fillRect(0, 0, 440, 440);
            cu = true;
        }
        if(cu && flag)
        {
            cu = false;
            c(0);
            if(em && cT)
            {
                cT = false;
                int l2 = 0;
                int i3 = 0;
                for(int k2 = 0; k2 < 11; k2++)
                {
                    if(dQ == '\004')
                        l2 = 10 - k2;
                    else
                    if(dQ == '\006')
                        l2 = k2 - 10;
                    else
                    if(dQ == '\002')
                        i3 = k2 - 10;
                    else
                    if(dQ == '\b')
                        i3 = 10 - k2;
                    a(g1, flag, l2, i3);
                    e(20);
                }

            } else
            {
                for(int l1 = 0; l1 < 11; l1++)
                {
                    for(int j1 = 0; j1 < 11; j1++)
                        c(g1, j1 + dW * 10, l1 + dX * 10);

                    e(20);
                }

            }
        }
        a(g1, flag, 0, 0);
        a(g1, true, true, true, true);
    }

    public void a(Graphics g1, boolean flag, int i1, int j1)
    {
        int k5 = 0;
        if(dB)
            return;
        bA.setColor(Color.gray);
        bA.fillRect(0, 0, 440, 440);
        for(int k2 = 0; k2 < 11; k2++)
        {
            for(int k1 = 0; k1 < 11; k1++)
            {
                short word0 = dY[k2 + dX * 10 + j1][k1 + dW * 10 + i1];
                bp = eb[word0][1];
                bA.drawImage(bo[bp], k1 * 40, k2 * 40, this);
            }

        }

        int j3 = be * 8;
        int i4 = bf * 8;
        if(i1 != 0)
            if(dQ == '\004')
                j3 = (10 - i1) * 40;
            else
            if(dQ == '\006')
                j3 = -i1 * 40;
        if(j1 != 0)
            if(dQ == '\002')
                i4 = -j1 * 40;
            else
            if(dQ == '\b')
                i4 = (10 - j1) * 40;
        if(eE == 0)
            bA.drawImage(bo[bq], j3, i4, this);
        for(int l2 = -1; l2 < 12; l2++)
        {
            for(int l1 = -1; l1 < 12; l1++)
            {
                int l4 = l1 + dW * 10 + i1;
                int i5 = l2 + dX * 10 + j1;
                if(l4 < 0 || i5 < 0 || l4 >= cD || i5 >= cD)
                    continue;
                short word1 = dZ[i5][l4];
                if(word1 == 0 || a(word1, be / 5, l1, bf / 5, l2))
                    continue;
                if(cj % 44 < 22 || ea[word1][2] == 0)
                    bp = ea[word1][1];
                else
                    bp = ea[word1][2];
                if(cU[l2 + 1][l1 + 1][0] > 0)
                    cU[l2 + 1][l1 + 1][0]--;
                if(cU[l2 + 1][l1 + 1][0] < 0)
                    cU[l2 + 1][l1 + 1][0]++;
                if(cU[l2 + 1][l1 + 1][1] > 0)
                    cU[l2 + 1][l1 + 1][1]--;
                if(cU[l2 + 1][l1 + 1][1] < 0)
                    cU[l2 + 1][l1 + 1][1]++;
                int k3 = l1 * 40 + cU[l2 + 1][l1 + 1][0] * 8;
                int j4 = l2 * 40 + cU[l2 + 1][l1 + 1][1] * 8;
                bA.drawImage(bo[bp], k3, j4, this);
            }

        }

        if(dr > 0 && em)
        {
            int i2;
            for(i2 = 3; i2 > 0 && ds[i2] == 0; i2--);
            k5 = (dt / dr) % (i2 + 1);
            dt++;
        }
        for(int i3 = 0; i3 < 11; i3++)
        {
            for(int j2 = 0; j2 < 11; j2++)
            {
                if(dr > 0 && em)
                    bA.drawImage(bo[ds[k5]], j2 * 40, i3 * 40, this);
                a(bA, j2, i3, j2 * 40, i3 * 40);
            }

        }

        if(a)
            a(3, dZ[dP][dO], dY[dP][dO], 0, 0, 0, g1, bA, bC, dO, dP);
        if(dw > 0 && em)
        {
            if(_flddo <= 0)
                dw++;
            int j5;
            if(dw < 5)
            {
                j5 = 0;
            } else
            {
                j5 = dw - 5;
                if(j5 >= 10 + dz)
                    dw = 0;
            }
            int l3 = dx + (((440 + (du % 3) * 40) - dx) * j5) / (10 + dz);
            int k4 = dy + (((140 + (du / 3) * 40) - dy) * j5) / (10 + dz);
            a(g1, true, true, true, true);
            if(eh[du] != 0)
            {
                g1.drawImage(bo[ea[eh[du]][1]], l3, k4, this);
                bA.drawImage(bo[ea[eh[du]][1]], l3, k4, this);
            }
        }
        if(flag && _flddo <= 0)
            g1.drawImage(bt, 0, 0, this);
    }

    public void c(int i1)
    {
        if(i1 != 0)
            dQ = (char)i1;
        if(dQ == '\002')
            bq = dU + 2;
        else
        if(dQ == '\004')
            bq = dU + 4;
        else
        if(dQ == '\006')
            bq = dU + 6;
        else
            bq = dU;
    }

    public void e(Graphics g1)
    {
        if(ck == 0)
        {
            c(0);
            if(be % 10 > 4 && (dQ == '\004' || dQ == '\006') || bf % 10 > 4 && (dQ == '\b' || dQ == '\002'))
                bq++;
        }
        if(em)
        {
            a(g1, true, 0, 0);
            return;
        }
        if(dQ == '\004' || dQ == '\006')
        {
            bD.setColor(Color.gray);
            bD.fillRect(0, 0, 80, 40);
            int k1 = be / 5;
            int i2 = bf / 5;
            if(be % 5 == 0 && dQ == '\006')
                k1--;
            if(be == 50 && dQ == '\004')
                k1--;
            int k2 = k1;
            int i3 = i2;
            if(k1 >= 0)
            {
                co = dY[i2 + dX * 10][k1 + dW * 10];
                bp = eb[co][1];
                bD.drawImage(bo[bp], 0, 0, this);
            }
            if(++k1 + dW * 10 < cD)
            {
                co = dY[i2 + dX * 10][k1 + dW * 10];
                bp = eb[co][1];
                bD.drawImage(bo[bp], 40, 0, this);
            }
            k1 = (be % 5) * 8;
            if(be % 5 == 0 && dQ == '\006')
                k1 += 40;
            if(be == 50 && dQ == '\004')
                k1 += 40;
            i2 = (bf % 5) * 8;
            if(eE == 0)
                bD.drawImage(bo[bq], k1, i2, this);
            k1 = k2;
            i2 = i3;
            if(k1 >= 0)
            {
                co = dZ[i2 + dX * 10][k1 + dW * 10];
                if(co != 0)
                {
                    if(cj % 44 < 22 || ea[co][2] == 0)
                        bp = ea[co][1];
                    else
                        bp = ea[co][2];
                    if(!a(co, k1, be / 5, i2, bf / 5))
                        bD.drawImage(bo[bp], 0, 0, this);
                }
            }
            if(++k1 + dW * 10 < cD)
            {
                co = dZ[i2 + dX * 10][k1 + dW * 10];
                if(co != 0)
                {
                    if(cj % 44 < 22 || ea[co][2] == 0)
                        bp = ea[co][1];
                    else
                        bp = ea[co][2];
                    if(!a(co, k1, be / 5, i2, bf / 5))
                        bD.drawImage(bo[bp], 40, 0, this);
                }
            }
            if(k2 >= 0)
                a(bD, k2, i3, 0, 0);
            a(bD, k2 + 1, i3, 40, 0);
            k1 = (be / 5) * 40;
            if(be % 5 == 0 && dQ == '\006')
                k1 -= 40;
            if(be == 50 && dQ == '\004')
                k1 -= 40;
            i2 = (bf / 5) * 40;
            g1.drawImage(bw, k1, i2, this);
        }
        if(dQ == '\b' || dQ == '\002')
        {
            bE.setColor(Color.gray);
            bE.fillRect(0, 0, 40, 80);
            int l1 = be / 5;
            int j2 = bf / 5;
            if(bf % 5 == 0 && dQ == '\002')
                j2--;
            int l2 = l1;
            int j3 = j2;
            if(j2 >= 0)
            {
                co = dY[j2 + dX * 10][l1 + dW * 10];
                bp = eb[co][1];
                bE.drawImage(bo[bp], 0, 0, this);
            }
            if(++j2 + dX * 10 < cD)
            {
                co = dY[j2 + dX * 10][l1 + dW * 10];
                bp = eb[co][1];
                bE.drawImage(bo[bp], 0, 40, this);
            }
            l1 = (be % 5) * 8;
            j2 = (bf % 5) * 8;
            if(bf % 5 == 0 && dQ == '\002')
                j2 += 40;
            if(eE == 0)
                bE.drawImage(bo[bq], l1, j2, this);
            l1 = l2;
            j2 = j3;
            if(j2 >= 0)
            {
                co = dZ[j2 + dX * 10][l1 + dW * 10];
                if(co != 0)
                {
                    if(cj % 44 < 22 || ea[co][2] == 0)
                        bp = ea[co][1];
                    else
                        bp = ea[co][2];
                    if(!a(co, l1, be / 5, j2, bf / 5))
                        bE.drawImage(bo[bp], 0, 0, this);
                }
            }
            if(++j2 + dX * 10 < cD)
            {
                co = dZ[j2 + dX * 10][l1 + dW * 10];
                if(co != 0)
                {
                    if(cj % 44 < 22 || ea[co][2] == 0)
                        bp = ea[co][1];
                    else
                        bp = ea[co][2];
                    if(!a(co, l1, be / 5, j2, bf / 5))
                        bE.drawImage(bo[bp], 0, 40, this);
                }
            }
            if(l2 >= 0)
                a(bE, l2, j3, 0, 0);
            a(bE, l2, j3 + 1, 0, 40);
            l1 = (be / 5) * 40;
            j2 = (bf / 5) * 40;
            if(bf % 5 == 0 && dQ == '\002')
                j2 -= 40;
            g1.drawImage(bx, l1, j2, this);
        }
        if(_flddo > 0)
            return;
        for(int j1 = 0; j1 < 11; j1++)
        {
            for(int i1 = 0; i1 < 11; i1++)
            {
                if(!cs[i1][j1] || be / 5 == i1 && bf / 5 == j1 || (be / 5 + 1 == i1 && bf / 5 == j1 && dQ == '\006' || bf / 5 + 1 == j1 && be / 5 == i1 && dQ == '\002') && (be % 5 != 0 || bf % 5 != 0))
                    continue;
                bB.setColor(Color.gray);
                bB.fillRect(0, 0, 40, 40);
                co = dY[j1 + dX * 10][i1 + dW * 10];
                bp = eb[co][1];
                bB.drawImage(bo[bp], 0, 0, this);
                co = dZ[j1 + dX * 10][i1 + dW * 10];
                if(co != 0 && !a(co, be / 5, i1, bf / 5, j1))
                {
                    if(cj % 44 < 22 || ea[co][2] == 0)
                        bp = ea[co][1];
                    else
                        bp = ea[co][2];
                    bB.drawImage(bo[bp], 0, 0, this);
                }
                a(bB, i1, j1, 0, 0);
                g1.drawImage(bu, i1 * 40, j1 * 40, this);
            }

        }

    }

    public void b()
    {
        for(int j1 = 0; j1 < 11; j1++)
        {
            for(int i1 = 0; i1 < 11; i1++)
            {
                cs[i1][j1] = false;
                co = dZ[j1 + dX * 10][i1 + dW * 10];
                if(co != 0 && cj % 11 == i1 && ea[co][2] != 0)
                    cs[i1][j1] = true;
            }

        }

        cj++;
    }

    public void a(Graphics g1, int i1, int j1, int k1, int l1)
    {
        bp = 0;
        if(i1 == 0 && j1 == 0)
            bp = dM;
        else
        if(i1 == 10 && j1 == 0)
            bp = dM + 2;
        else
        if(i1 == 0 && j1 == 10)
            bp = dM + 20;
        else
        if(i1 == 10 && j1 == 10)
            bp = dM + 22;
        else
        if(j1 == 0)
            bp = dM + 1;
        else
        if(i1 == 0)
            bp = dM + 10;
        else
        if(i1 == 10)
            bp = dM + 12;
        else
        if(j1 == 10)
            bp = dM + 21;
        if(bp != 0)
            g1.drawImage(bo[bp], k1, l1, this);
    }

    public boolean a(int i1, int j1, int k1)
    {
        ck = 0;
        if(a)
            a(2, dZ[dP][dO], dY[dP][dO], i1, j1, k1, null, bA, bC, dO, dP);
        if(cO || et)
            return true;
        if(bR)
        {
            bR = false;
            ct = true;
            cu = false;
        }
        if(bO)
        {
            ct = true;
            cu = false;
            bO = false;
            System.gc();
            return true;
        }
        if(eP)
        {
            eP = false;
            if(eQ)
                l();
            eM.dispose();
        }
        return false;
    }

    public void d(int i1)
    {
        if(ea[eh[i1]][4] != 0)
        {
            cy = i1;
            bH = 9;
            bL = true;
            cz = eh[i1];
        }
    }

    public boolean keyDown(Event event, int i1)
    {
        if(a(i1, 0, 0))
            return true;
        if(bH != 0)
        {
            if(i1 == 10 || i1 == 13 || i1 == 121 || i1 == 89)
                bI = 1;
            if(i1 == 27 || i1 == 110 || i1 == 78)
                bI = 2;
            return true;
        }
        if((i1 == 10 || i1 == 13 || i1 == 32 || i1 == 27) && !ch)
        {
            ch = true;
            return true;
        }
        if(_flddo > 0)
            return true;
        if((i1 == 1008 || i1 == 109 || i1 == 77) && ch && bH == 0)
        {
            bR = true;
            ch = false;
        }
        if(i1 == 1015)
        {
            bH = 3;
            bL = true;
        }
        if(i1 == 1014)
        {
            bH = 5;
            bL = true;
        }
        if(i1 == 1013)
        {
            bH = 10;
            bL = true;
        }
        if(i1 == 1012)
        {
            if(bQ)
                bH = 11;
            else
                bH = 14;
            bL = true;
        }
        if(i1 == 1010)
        {
            bH = 14;
            bL = true;
        }
        if(i1 == 1011)
        {
            bH = 13;
            bL = true;
        }
        if(i1 == 1019)
            b(eG + en, true);
        if(i1 == 1016 || i1 == 103 || i1 == 71)
        {
            if(em)
            {
                b("高速描画（部分画面描画）モードに\n切り換えます。\n動作が重い低スペックのマシンで\n使用してください。", true);
                em = false;
            } else
            {
                b("通常描画（全画面描画）モードに\n切り換えます。", true);
                em = true;
            }
            ct = true;
        }
        if(i1 == 1009 || i1 == 112 || i1 == 80)
            if(el == 20)
            {
                b("移動速度を高速に切り換えます。\n戦闘も速くなります。", true);
                el = 10;
            } else
            {
                b("移動速度を通常に切り換えます。", true);
                el = 20;
            }
        if(i1 == 105 || i1 == 73)
            if(el != 60)
            {
                b("移動速度を低速に切り換えます。", true);
                el = 60;
            } else
            {
                b("移動速度を通常に切り換えます。", true);
                el = 20;
            }
        if(i1 == 108 || i1 == 76)
            if(!cW)
            {
                b("リンクを別のウィンドウで\n開くようにします。", true);
                cW = true;
            } else
            if(cW)
            {
                b("リンクを同じウィンドウで\n開くようにします。", true);
                cW = false;
            }
        if(i1 == 1004)
        {
            cc = true;
            cg = 8;
        } else
        if(i1 == 1005)
        {
            cd = true;
            cg = 2;
        } else
        if(i1 == 1006)
        {
            ce = true;
            cg = 4;
        } else
        if(i1 == 1007)
        {
            cf = true;
            cg = 6;
        } else
        if(i1 == 1000)
        {
            ct = true;
            cu = true;
        }
        char ac1[] = {
            '1', '!', '2', '"', '3', '#', 'q', 'Q', 'w', 'W', 
            'e', 'E', 'a', 'A', 's', 'S', 'd', 'D', 'z', 'Z', 
            'x', 'X', 'c', 'C'
        };
        for(int j1 = 0; j1 < 12; j1++)
            if(i1 == ac1[j1 * 2] || i1 == ac1[j1 * 2 + 1])
                d(j1);

        if(bL || bR)
            a(1);
        return true;
    }

    public boolean keyUp(Event event, int i1)
    {
        if(i1 == 1004)
            cc = false;
        else
        if(i1 == 1005)
            cd = false;
        else
        if(i1 == 1006)
            ce = false;
        else
        if(i1 == 1007)
        {
            cf = false;
        } else
        {
            cc = false;
            cd = false;
            ce = false;
            cf = false;
        }
        return true;
    }

    public boolean mouseDown(Event event, int i1, int j1)
    {
        boolean flag = false;
        if(a(0, i1, j1))
            return true;
        if(!ch)
        {
            ch = true;
            return true;
        }
        if(bH != 0)
        {
            if(i1 > bJ && i1 < bJ + 40 && j1 > bK && j1 < bK + 40)
                bI = 1;
            if(i1 > bJ + 40 && i1 < bJ + 80 && j1 > bK && j1 < bK + 40)
                bI = 2;
            return true;
        }
        if(_flddo > 0)
            return true;
        if(i1 > 440 && i1 < 560 && j1 > 0 && j1 < 140 && bH == 0)
            b(eG + en, true);
        if(i1 > 0 && i1 < 440 && j1 > 0 && j1 < 440)
        {
            int l1;
            if(j1 >= bf * 8 + 20)
                l1 = j1 - (bf * 8 + 20);
            else
                l1 = (bf * 8 + 20) - j1;
            int k1;
            if(i1 >= be * 8 + 20)
                k1 = i1 - (be * 8 + 20);
            else
                k1 = (be * 8 + 20) - i1;
            if(bf * 8 + 20 < j1 && l1 > k1)
                cd = true;
            else
            if(bf * 8 + 20 > j1 && l1 > k1)
                cc = true;
            else
            if(be * 8 + 20 < i1 && l1 < k1)
                cf = true;
            else
            if(be * 8 + 20 > i1 && l1 < k1)
                ce = true;
        }
        if(i1 > 440 && i1 < 560 && j1 > 405 && j1 < 440)
        {
            bH = 3;
            bL = true;
        }
        if(i1 > 440 && i1 < 560 && j1 > 370 && j1 < 405)
        {
            bH = 5;
            bL = true;
        }
        if(i1 > 440 && i1 < 560 && j1 > 335 && j1 < 370)
        {
            bH = 10;
            bL = true;
        }
        if(i1 > 440 && i1 < 560 && j1 > 300 && j1 < 335)
        {
            if(bQ)
                bH = 11;
            else
                bH = 14;
            bL = true;
        }
        for(int i2 = 0; i2 < 12; i2++)
            if(i1 > 40 * (11 + i2 % 3) && i1 < 40 * (12 + i2 % 3) && j1 > 140 + 40 * (i2 / 3) && j1 < 180 + 40 * (i2 / 3))
                d(i2);

        if(bL || bR)
            a(1);
        return true;
    }

    public boolean mouseUp(Event event, int i1, int j1)
    {
        cc = false;
        cd = false;
        ce = false;
        cf = false;
        return true;
    }

    public boolean a(int i1, int j1, int k1, int l1, int i2)
    {
        if(be % 5 != 0 || bf % 5 != 0)
            return false;
        return j1 == k1 && l1 == i2 && ew == 0 && (ea[i1][15] == 0 && ea[i1][3] == 5 || ea[i1][3] == 3 || ea[i1][3] == 1 || ea[i1][3] == 4 || ea[i1][3] == 14 || ea[i1][3] == 15 || ea[i1][3] == 17 || ea[i1][3] == 18);
    }

    public void a(int i1, int j1, int k1, int l1)
    {
        if(ex == 1 && em && dw == 0)
        {
            du = i1;
            if(j1 != 0)
                du = j1 - 1;
            dv = eh[du];
            dw = 1;
            dx = (k1 - dW * 10) * 40;
            dy = (l1 - dX * 10) * 40;
            dz = 10 - (k1 - dW * 10);
        }
    }

    public boolean a(Graphics g1, int i1, int j1)
    {
        boolean flag = true;
        ez = i1;
        eA = j1;
        co = dY[j1][i1];
        if(co == 0 && dg == 0)
            flag = false;
        if(eb[co][3] == 1)
        {
            if(a)
            {
                a(0, 0, co, 0, 0, 0, null, bA, bC, i1, j1);
                if(ey)
                    return false;
            }
            int k2 = eb[co][5];
            if(k2 != 0)
            {
                dT = k2;
                ep = true;
            }
            b(g1, i1, j1, 1);
            if(eb[co][19] != 0)
                a(eb[co][19]);
            flag = false;
        }
        co = dZ[j1][i1];
        int l2 = co;
        if(co != 0)
        {
            if(a)
            {
                a(0, co, 0, 0, 0, 0, null, bA, bC, i1, j1);
                if(ey && ea[co][3] != 0 && (ea[co][3] != 5 || ea[co][15] != 1))
                    return false;
            }
            flag = false;
            if(ea[co][3] == 0)
            {
                if(ea[co][4] == 1 && eb[dY[j1][i1]][3] != 1)
                    flag = true;
            } else
            if(ea[co][3] == 2)
            {
                ep = false;
                dT = ea[co][5];
                a(new String(dR[dT]), true);
            } else
            if(ea[co][3] == 3)
            {
                if(ea[co][11] > 30000 && ee < ea[co][11] - 30000 || ea[co][12] > 30000 && ef < ea[co][12] - 30000 || ea[co][13] > 30000 && eg < ea[co][13] - 30000)
                {
                    dZ[j1][i1] = 0;
                    c(g1, i1, j1);
                    return false;
                }
                if(ea[co][10] > 30000)
                {
                    ec -= ea[co][10] - 30000;
                    if(ec <= 0 && ea[co][10] != 0)
                    {
                        j(g1);
                        return false;
                    }
                } else
                {
                    ec += ea[co][10];
                }
                if(ea[co][11] > 30000)
                    ee -= ea[co][11] - 30000;
                else
                    ee += ea[co][11];
                if(ea[co][12] > 30000)
                    ef -= ea[co][12] - 30000;
                else
                    ef += ea[co][12];
                if(ea[co][13] > 30000)
                    eg -= ea[co][13] - 30000;
                else
                    eg += ea[co][13];
                if(ea[co][10] != 0)
                    dA[0] = 20;
                if(ea[co][11] != 0)
                    dA[1] = 20;
                if(ea[co][12] != 0)
                    dA[2] = 20;
                if(ea[co][13] != 0)
                    dA[3] = 20;
                a(g1, true, true, true, false);
                ep = true;
                dT = ea[co][5];
                dZ[j1][i1] = 0;
                b(g1, i1, j1, 0, l2);
            } else
            if(ea[co][3] == 4)
            {
                int k1;
                for(k1 = 0; k1 < 12 && eh[k1] != 0; k1++);
                int k3 = ea[co][15];
                if(k1 == 12 && (k3 == 0 || ea[eh[k3 - 1]][15] == 0))
                {
                    ep = true;
                    dT = 1;
                    if(dS[1].equals(""))
                        dR[dT] = "これ以上、アイテムを持てません。";
                    else
                        dR[dT] = dS[1];
                } else
                {
                    if(ea[co][11] != 0)
                        dA[1] = 20;
                    if(ea[co][12] != 0)
                        dA[2] = 20;
                    a(k1, k3, i1, j1);
                    f(co);
                    a(g1, true, true, true, false);
                    dZ[j1][i1] = 0;
                    if(ea[co][4] != 0)
                    {
                        if(bX)
                        {
                            if(dS[0].equals(""))
                                b("このアイテムは右のボックスを\nクリックすることで使用できます。\n使用できるアイテムは\n色枠で囲まれます。", false);
                            else
                            if(!dS[0].equals("BLANK"))
                                b(dS[0], false);
                            bX = false;
                        }
                    } else
                    {
                        ep = true;
                        dT = ea[co][5];
                        b(g1, i1, j1, 0, l2);
                    }
                }
            } else
            if(ea[co][3] == 5)
            {
                int l1 = 0;
                do
                {
                    if(l1 >= 12)
                        break;
                    if(ea[co][14] == eh[l1])
                    {
                        if(ey)
                            return false;
                        if(ea[co][4] == 0)
                        {
                            eh[l1] = 0;
                            f(0);
                        }
                        a(g1, true, true, true, false);
                        ep = true;
                        dT = ea[co][5];
                        dZ[j1][i1] = 0;
                        if(em)
                            dB = true;
                        b(g1, i1, j1, 0, l2);
                        cw = 1;
                        if(ea[co][19] != 0)
                            a(ea[co][19]);
                        break;
                    }
                    l1++;
                } while(true);
                if(l1 == 12 && ea[co][15] == 1)
                    return true;
            } else
            if(ea[co][3] == 6)
            {
                cO = true;
                cP = true;
                cI = ea[co][10];
                cJ = ea[co][11];
                cK = ea[co][12];
                cL = ea[co][13];
                cM = i1;
                cN = j1;
                ep = false;
                cQ = 0;
                b(g1, co);
                e(200);
            } else
            if(ea[co][3] == 1)
            {
                if(ea[co][15] != 0)
                    a(ea[co][15] * 100, g1);
                dT = ea[co][5];
                if(dT != 0)
                    ep = true;
                if(i1 == dO && j1 == dP && ew == 0)
                    dZ[j1][i1] = 0;
                b(g1, i1, j1, 0, l2);
            }
            if(ea[co][3] == 11)
            {
                bN = true;
                ep = true;
                dT = ea[co][5];
                if(dT == 0)
                {
                    dT = 1;
                    dR[dT] = "スコアを表示します。";
                }
                cC = 0L;
                cC += ea[co][10] * ec;
                cC += ea[co][11] * (ee + cA);
                cC += ea[co][12] * (ef + cB);
                cC += ea[co][13] * eg;
            } else
            if(ea[co][3] == 15)
            {
                cp = co;
                cq = i1;
                cr = j1;
                dT = ea[co][5];
                ep = false;
                bH = 7;
                bL = true;
                if(i1 == dO && j1 == dP && ew == 0)
                    dZ[j1][i1] = 0;
            } else
            if(ea[co][3] == 14)
            {
                cp = co;
                cq = i1;
                cr = j1;
                dT = ea[co][5];
                ep = false;
                bH = 6;
                bL = true;
                if(i1 == dO && j1 == dP && ew == 0)
                    dZ[j1][i1] = 0;
            } else
            if(ea[co][3] == 17)
            {
                cp = co;
                cq = i1;
                cr = j1;
                dT = ea[co][5];
                ep = false;
                bH = 12;
                bL = true;
                if(i1 == dO && j1 == dP && ew == 0)
                    dZ[j1][i1] = 0;
            } else
            if(ea[co][3] == 18)
            {
                if(i1 == dO && j1 == dP && ew == 0)
                    dZ[j1][i1] = 0;
                b(g1, i1, j1, 0, l2);
                int i3 = ea[co][16];
                int j3 = ea[co][17];
                a(i3, j3);
                if(ea[co][19] != 0)
                    a(ea[co][19]);
                return false;
            }
            if(ea[co][3] != 6 && ea[co][3] != 5)
                a(ea[co][19]);
        }
        int i2 = i1 % 10;
        if(i2 == 0 && be / 5 >= 9)
            i2 = 10;
        int j2 = j1 % 10;
        if(j2 == 0 && bf / 5 >= 9)
            j2 = 10;
        cs[i2][j2] = true;
        return flag;
    }

    public boolean a(Graphics g1, int i1, int j1, int k1)
    {
        ez = dO;
        eA = dP;
        if(a)
        {
            a(0, 0, i1, 0, 0, 0, null, bA, bC, dO, dP);
            if(ey)
                return false;
        }
        if(eb[i1][3] == 0)
        {
            if(eb[i1][14] != 0)
            {
                int l1;
                for(l1 = 0; l1 < 12 && eh[l1] != eb[i1][14]; l1++);
                if(l1 == 12)
                    return false;
            }
            b(g1, j1 + dW * 10, k1 + dX * 10, 1);
            if(eb[i1][19] != 0 && bs)
                a(eb[i1][19]);
            if(eb[i1][15] != 0)
                a(eb[i1][15] * 100, g1);
            int i2 = eb[i1][5];
            if(i2 != 0)
            {
                dT = i2;
                ep = true;
                return true;
            }
            if(eb[i1][15] != 0)
                return true;
        } else
        {
            if(eb[i1][3] == 2)
            {
                b(g1, dO, dP, 1);
                int j2 = eb[i1][16];
                int k2 = eb[i1][17];
                a(j2, k2);
                if(eb[i1][19] != 0 && bs)
                    a(eb[i1][19]);
                return true;
            }
            if(eb[i1][3] == 4)
            {
                dT = eb[i1][5];
                a(new String(dR[dT]), true);
                return true;
            }
        }
        return false;
    }

    public boolean f(Graphics g1)
    {
        int i1 = be / 5;
        int j1 = bf / 5;
        int k1 = dY[j1 + dX * 10][i1 + dW * 10];
        int l1 = dZ[j1 + dX * 10][i1 + dW * 10];
        if(k1 != eH || eJ != i1 + dW * 10 || eK != j1 + dX * 10)
        {
            eJ = i1 + dW * 10;
            eK = j1 + dX * 10;
            eH = k1;
            if(a(g1, k1, i1, j1))
                return true;
        }
        if(l1 != eI || eJ != i1 + dW * 10 || eK != j1 + dX * 10)
        {
            eJ = i1 + dW * 10;
            eK = j1 + dX * 10;
            eI = l1;
            l1 = dZ[j1 + dX * 10][i1 + dW * 10];
            if(ea[l1][3] != 0)
            {
                c(g1, i1 + dW * 10, j1 + dX * 10);
                a(g1, be / 5 + dW * 10, bf / 5 + dX * 10);
                return true;
            }
        }
        return false;
    }

    public boolean b(int i1, int j1, int k1)
    {
        if(i1 >= 0 && i1 <= cD - 1 && j1 >= 0 && j1 <= cD - 1)
        {
            short word0 = dY[j1][i1];
            if(eb[word0][3] == 2)
            {
                c(k1);
                return true;
            }
            word0 = dZ[j1][i1];
            if(ea[word0][3] == 18)
            {
                c(k1);
                return true;
            }
        }
        return false;
    }

    public void a(int i1, int j1)
    {
        if(i1 > 9000)
            i1 = (dO + i1) - 10000;
        if(j1 > 9000)
            j1 = (dP + j1) - 10000;
        if(i1 >= 0 && i1 <= cD - 1 && j1 >= 0 && j1 <= cD - 1)
        {
            int k1 = dW;
            int l1 = dX;
            if(i1 == cD - 1)
            {
                dW = i1 / 10 - 1;
                be = 50;
            } else
            {
                dW = i1 / 10;
                be = (i1 % 10) * 5;
            }
            if(j1 == cD - 1)
            {
                dX = j1 / 10 - 1;
                bf = 50;
            } else
            {
                dX = j1 / 10;
                bf = (j1 % 10) * 5;
            }
            ct = true;
            if(dW == k1 && dX == l1)
            {
                cu = false;
            } else
            {
                cu = true;
                cS = true;
            }
            dO = be / 5 + dW * 10;
            dP = bf / 5 + dX * 10;
            if(bM)
            {
                c(2);
                if(!b(dO, dP, 2))
                {
                    b(dO - 1, dP, 6);
                    b(dO + 1, dP, 4);
                    b(dO, dP - 1, 2);
                    b(dO, dP + 1, 8);
                }
            }
        }
    }

    public void g(Graphics g1)
    {
        if(cV > 0)
        {
            if(cV % 5 == 0)
                h();
            cV--;
            return;
        }
        if(be % 5 == 0 && bf % 5 == 0)
        {
            if(f(g1))
                return;
            byte byte0;
            if(cc && cg == 8)
                byte0 = 8;
            else
            if(cd && cg == 2)
                byte0 = 2;
            else
            if(ce && cg == 4)
                byte0 = 4;
            else
            if(cf && cg == 6)
                byte0 = 6;
            else
                byte0 = 0;
            if(!cc && !cd && !ce && !cf)
            {
                if(ck >= 10)
                {
                    if(!em)
                    {
                        int j1 = dW * 10 + ck % 11;
                        for(int i1 = 0; i1 < 11; i1++)
                        {
                            int k2 = dX * 10 + i1;
                            if(ea[dZ[k2][j1]][2] == 0)
                                c(g1, j1, k2);
                        }

                    }
                    if(ck % 11 == 0)
                        a(g1, false, false, false, false);
                }
                ck++;
            } else
            {
                ck = 0;
            }
            if(cS)
            {
                int l3;
                if(el <= 10)
                    l3 = ck / 2;
                else
                    l3 = ck;
                if(l3 >= 64 && l3 % 8 == 0 && ck % 4 == 0)
                    if(l3 % 16 == 0)
                    {
                        if(dQ == '\002')
                            dQ = '\004';
                        else
                        if(dQ == '\004')
                            dQ = '\b';
                        else
                        if(dQ == '\006')
                            dQ = '\002';
                        else
                        if(dQ == '\b')
                            dQ = '\006';
                        c(dQ);
                    } else
                    {
                        c(dQ);
                        bq++;
                    }
            }
            if(byte0 == 8 || byte0 == 0 && cc)
            {
                dQ = '\b';
                if(bf == 0 && dX != 0)
                {
                    if(dY[dP - 1][dO] != 0 || dg == 1)
                    {
                        dX--;
                        ct = true;
                        cu = true;
                        cT = true;
                        bf = 50;
                    }
                } else
                if(bf != 0)
                {
                    int k1 = be / 5 + dW * 10;
                    int l2 = (bf / 5 + dX * 10) - 1;
                    if(a(g1, k1, l2))
                    {
                        bf = bf - bg;
                        dP = l2;
                        h();
                    }
                }
            } else
            if(byte0 == 2 || byte0 == 0 && cd)
            {
                dQ = '\002';
                if(bf == 50 && dX != cD / 10 - 1)
                {
                    if(dY[dP + 1][dO] != 0 || dg == 1)
                    {
                        dX++;
                        ct = true;
                        cu = true;
                        cT = true;
                        bf = 0;
                    }
                } else
                if(bf != 50)
                {
                    int l1 = be / 5 + dW * 10;
                    int i3 = bf / 5 + dX * 10 + 1;
                    if(a(g1, l1, i3))
                    {
                        bf = bf + bg;
                        dP = i3;
                        h();
                    }
                }
            } else
            if(byte0 == 4 || byte0 == 0 && ce)
            {
                dQ = '\004';
                if(be == 0 && dW != 0)
                {
                    if(dY[dP][dO - 1] != 0 || dg == 1)
                    {
                        dW--;
                        ct = true;
                        cu = true;
                        cT = true;
                        be = 50;
                    }
                } else
                if(be != 0)
                {
                    int i2 = (be / 5 + dW * 10) - 1;
                    int j3 = bf / 5 + dX * 10;
                    if(a(g1, i2, j3))
                    {
                        be = be - bg;
                        dO = i2;
                        h();
                    }
                }
            } else
            if(byte0 == 6 || byte0 == 0 && cf)
            {
                dQ = '\006';
                if(be == 50 && dW != cD / 10 - 1)
                {
                    if(dY[dP][dO + 1] != 0 || dg == 1)
                    {
                        dW++;
                        ct = true;
                        cu = true;
                        cT = true;
                        be = 0;
                    }
                } else
                if(be != 50)
                {
                    int j2 = be / 5 + dW * 10 + 1;
                    int k3 = bf / 5 + dX * 10;
                    if(a(g1, j2, k3))
                    {
                        be = be + bg;
                        dO = j2;
                        h();
                    }
                }
            }
        } else
        {
            if(dQ == '\b')
                bf = bf - bg;
            else
            if(dQ == '\002')
                bf = bf + bg;
            else
            if(dQ == '\004')
                be = be - bg;
            else
            if(dQ == '\006')
                be = be + bg;
            dO = be / 5 + dW * 10;
            dP = bf / 5 + dX * 10;
            if(be % 5 == 0 && bf % 5 == 0 && a)
                a(1, dZ[dP][dO], dY[dP][dO], 0, 0, 0, null, bA, bC, dO, dP);
        }
    }

    public String a(String s1, int i1)
    {
        dp = i1 - 1;
        String s2 = a(s1, "\n");
        s2 = s2.toLowerCase();
        s2 = s2.trim();
        s2 = s2.replace('.', ',');
        int j1 = 0;
        do
        {
            if(j1 >= s2.length())
                break;
            int k1 = s2.indexOf(" ", 0);
            if(k1 == -1)
                break;
            s2 = s2.substring(0, k1) + s2.substring(k1 + 1);
            j1++;
        } while(true);
        return s2;
    }

    public String a(String s1, String s2)
    {
        String s3 = "";
        if(dp == -2)
            return s3;
        int i1 = dp + 1;
        dp = s1.indexOf(s2, i1);
        if(dp == -1)
        {
            dp = -2;
            s3 = s1.substring(i1);
        } else
        {
            s3 = s1.substring(i1, dp);
        }
        return s3;
    }

    public int a(int i1, String s1, String s2, int j1)
    {
        int k1 = s2.indexOf(s1);
        if(k1 != -1 && k1 < j1)
            return i1;
        else
            return 0;
    }

    public int a(Graphics g1, String s1, int i1, int j1, int k1, boolean flag, boolean flag1, 
            boolean flag2)
    {
        int j6 = 1;
        boolean flag3 = false;
        char ac1[] = new char[1];
        String s4 = new String("");
        if(!flag)
        {
            Font font = new Font("Monospaced", 0, 18);
            g1.setFont(font);
        }
        int l6 = s1.indexOf("\n", s1.length() - 1);
        if(l6 == s1.length() - 1)
            s1 = s1.substring(0, s1.length() - 1);
        int i8 = 0;
        int l5 = i1;
        int i6 = j1;
        String s2 = s1;
        String s3 = s2.toLowerCase();
        l6 = s3.indexOf("<p>");
        if(l6 != -1 && _flddo >= 0)
        {
            for(int l1 = 0; l1 < _flddo; l1++)
            {
                int i7 = s3.indexOf("<p>");
                if((i7 += 3) >= s2.length())
                    i7--;
                if(s2.charAt(i7) == '\n')
                    i7++;
                s2 = s2.substring(i7, s2.length());
                s3 = s2.toLowerCase();
            }

            if(!flag1)
                _flddo++;
            int j7 = s3.indexOf("<p>");
            if(j7 != -1)
            {
                s1 = s2.substring(0, j7);
                ep = true;
            } else
            {
                s1 = s2;
                if(!flag1)
                    _flddo = -1;
            }
        }
        int j8 = s1.length();
        if(j8 == 0)
            return 0;
        for(int i2 = 0; i2 < j8; i2++)
        {
            if(i8 == 0)
            {
                String s5 = a(s1, i2);
                int k6 = a(1, "$imgplayer=", s5, k1);
                if(k6 == 0)
                    k6 = a(2, "$imgyesno=", s5, k1);
                if(k6 == 0)
                    k6 = a(3, "$hpmax=", s5, k1);
                if(k6 == 0)
                    k6 = a(4, "$save=", s5, k1);
                if(k6 == 0)
                    k6 = a(5, "$item=", s5, k1);
                if(k6 == 0)
                    k6 = a(6, "$default=", s5, k1);
                if(k6 == 0)
                    k6 = a(7, "$oldmap=", s5, k1);
                if(k6 == 0)
                    k6 = a(8, "$parts=", s5, k1);
                if(k6 == 0)
                    k6 = a(9, "$move=", s5, k1);
                if(k6 == 0)
                    k6 = a(10, "$map=", s5, k1);
                if(k6 == 0)
                    k6 = a(11, "$dirmap=", s5, k1);
                if(k6 == 0)
                    k6 = a(12, "$imgframe=", s5, k1);
                if(k6 == 0)
                    k6 = a(13, "$imgbom=", s5, k1);
                if(k6 == 0)
                    k6 = a(14, "$delplayer=", s5, k1);
                if(k6 == 0)
                    k6 = a(15, "$face=", s5, k1);
                if(k6 == 0)
                    k6 = a(16, "$effect=", s5, k1);
                if(k6 == 0)
                    k6 = a(17, "$gameover=", s5, k1);
                if(k6 == 0)
                    k6 = a(18, "$imgclick=", s5, k1);
                if(k6 == 0)
                    k6 = a(19, "$status=", s5, k1);
                if(k6 == 0)
                    k6 = a(20, "$effitem=", s5, k1);
                if(k6 == 0 && s5.indexOf("$", 0) == 0)
                    k6 = 99;
                if(k6 != 0)
                {
                    dp = -1;
                    a(s5, "=");
                    if(!flag2 && k6 != 15)
                        k6 = 0;
                    if(k6 == 1 || k6 == 2 || k6 == 13 || k6 == 17 || k6 == 18)
                    {
                        int k2 = a(a(s5, ","));
                        int j4 = a(a(s5, ","));
                        if(k6 == 1)
                            dU = k2 + j4 * 10;
                        else
                        if(k6 == 2)
                            g(k2 + j4 * 10);
                        else
                        if(k6 == 13)
                            dK = k2 + j4 * 10;
                        else
                        if(k6 == 17)
                        {
                            ej = k2;
                            ek = j4;
                        } else
                        if(k6 == 18)
                            dV = k2 + j4 * 10;
                    } else
                    if(k6 == 3)
                        ed = a(a(s5, ","));
                    else
                    if(k6 == 4)
                        ev = a(a(s5, ","));
                    else
                    if(k6 == 5)
                    {
                        if(flag)
                        {
                            int k8 = a(a(s5, ","));
                            int l8 = a(a(s5, ","));
                            if(k8 == 0)
                            {
                                f(l8);
                            } else
                            {
                                eh[k8 - 1] = l8;
                                f(0);
                            }
                        }
                        eq = true;
                    } else
                    if(k6 == 6)
                        ew = a(a(s5, ","));
                    else
                    if(k6 == 7)
                        dg = a(a(s5, ","));
                    else
                    if(k6 == 8)
                    {
                        short word0 = (short)a(a(s5, ","));
                        short word1 = (short)a(a(s5, ","));
                        int i9 = a(a(s5, ","));
                        int j9 = a(a(s5, ","));
                        if(j9 == 0)
                        {
                            for(int l2 = 0; l2 < cD; l2++)
                            {
                                for(int k4 = 0; k4 < cD; k4++)
                                {
                                    if(i9 == 1 && word0 == dY[k4][l2])
                                    {
                                        dY[k4][l2] = word1;
                                        continue;
                                    }
                                    if(i9 == 0 && word0 == dZ[k4][l2])
                                        dZ[k4][l2] = word1;
                                }

                            }

                        } else
                        {
                            for(int i3 = 0; i3 < 11; i3++)
                            {
                                for(int l4 = 0; l4 < 11; l4++)
                                {
                                    if(i9 == 1 && word0 == dY[l4 + dX * 10][i3 + dW * 10])
                                    {
                                        dY[l4 + dX * 10][i3 + dW * 10] = word1;
                                        continue;
                                    }
                                    if(i9 == 0 && word0 == dZ[l4 + dX * 10][i3 + dW * 10])
                                        dZ[l4 + dX * 10][i3 + dW * 10] = word1;
                                }

                            }

                        }
                        if(!em)
                            ct = true;
                    } else
                    if(k6 == 9)
                        cV = a(a(s5, ",")) * 5;
                    else
                    if(k6 == 10)
                    {
                        int k9 = a(a(s5, ","));
                        String s6 = a(s5, ",");
                        int j3 = a(s6);
                        String s7 = a(s5, ",");
                        int i5 = a(s7);
                        int i10 = a(a(s5, ","));
                        int i11 = ez;
                        int k11 = eA;
                        if(s6.indexOf("+", 0) == 0)
                        {
                            s6 = s6.replace('+', '0');
                            j3 = a(s6);
                            i11 += j3;
                        } else
                        if(s6.indexOf("-", 0) == 0)
                            i11 += j3;
                        else
                            i11 = j3;
                        if(s7.indexOf("+", 0) == 0)
                        {
                            s7 = s7.replace('+', '0');
                            i5 = a(s7);
                            k11 += i5;
                        } else
                        if(s7.indexOf("-", 0) == 0)
                            k11 += i5;
                        else
                            k11 = i5;
                        if(s6.indexOf("p", 0) == 0)
                            i11 = dO;
                        if(s7.indexOf("p", 0) == 0)
                            k11 = dP;
                        if(i11 >= 0 && i11 <= cD - 1 && k11 >= 0 && k11 <= cD - 1)
                            if(i10 == 0)
                                dZ[k11][i11] = (short)k9;
                            else
                                dY[k11][i11] = (short)k9;
                        if(!em)
                            ct = true;
                    } else
                    if(k6 == 11)
                    {
                        int l9 = a(a(s5, ","));
                        int k3 = a(a(s5, ","));
                        int j10 = a(a(s5, ","));
                        int j11 = dO;
                        int l11 = dP;
                        if(dQ == '\006')
                            j11 += k3;
                        else
                        if(dQ == '\004')
                            j11 -= k3;
                        else
                        if(dQ == '\002')
                            l11 += k3;
                        else
                        if(dQ == '\b')
                            l11 -= k3;
                        if(j11 >= 0 && j11 <= cD - 1 && l11 >= 0 && l11 <= cD - 1)
                            if(j10 == 0)
                                dZ[l11][j11] = (short)l9;
                            else
                                dY[l11][j11] = (short)l9;
                        if(!em)
                            ct = true;
                    } else
                    if(k6 == 12)
                    {
                        int k10 = a(a(s5, ","));
                        int l3 = a(a(s5, ","));
                        int j5 = a(a(s5, ","));
                        if(k10 == 0)
                            dG = l3 + j5 * 10;
                        else
                        if(k10 == 1)
                            dH = l3 + j5 * 10;
                        else
                        if(k10 == 2)
                            dI = l3 + j5 * 10;
                        else
                        if(k10 == 3)
                            dJ = l3 + j5 * 10;
                        else
                        if(k10 == 4)
                            dL = l3 + j5 * 10;
                        else
                        if(k10 == 5)
                            dN = l3 + j5 * 10;
                        else
                        if(k10 == 6)
                            dM = l3 + j5 * 10;
                    } else
                    if(k6 == 14)
                        eE = a(a(s5, ","));
                    else
                    if(k6 == 15)
                    {
                        int j12 = a(a(s5, ","));
                        int l12 = a(a(s5, ","));
                        int j13 = a(a(s5, ","));
                        int k13 = a(a(s5, ","));
                        int l13 = a(a(s5, ","));
                        int i14 = a(a(s5, ","));
                        for(int i4 = 0; i4 < l13; i4++)
                        {
                            for(int k5 = 0; k5 < i14; k5++)
                            {
                                int i12 = i4 + j13 + (k5 + k13) * 10;
                                g1.drawImage(bo[i12], j12 + i4 * 40, l12 + k5 * 40, this);
                            }

                        }

                    } else
                    if(k6 == 16)
                    {
                        dr = a(a(s5, ","));
                        for(int j2 = 0; j2 < 4; j2++)
                        {
                            int k12 = a(a(s5, ","));
                            int i13 = a(a(s5, ","));
                            ds[j2] = k12 + i13 * 10;
                        }

                    } else
                    if(k6 == 19)
                    {
                        int l10 = a(a(s5, ","));
                        if(l10 == 0)
                            ec = a(a(s5, ","));
                        else
                        if(l10 == 1)
                            ee = a(a(s5, ","));
                        else
                        if(l10 == 2)
                            ef = a(a(s5, ","));
                        else
                        if(l10 == 3)
                            eg = a(a(s5, ","));
                        else
                        if(l10 == 4)
                            en = a(a(s5, ","));
                    } else
                    if(k6 == 20)
                        ex = a(a(s5, ","));
                    int k7 = s1.indexOf("\n", i2);
                    if(i2 == 0 && k7 == -1)
                        return 0;
                    i2 = k7;
                    if(i2 == -1)
                        return j6 - 1;
                    continue;
                }
                int l7 = s5.indexOf("<c>");
                if(l7 != -1 && l7 < k1)
                    if(i2 == 0)
                        return 0;
                    else
                        return j6 - 1;
            }
            if(s1.charAt(i2) == '\n')
            {
                l5 = i1;
                i6 += 22;
                i8 = 0;
                if(i2 < j8 - 1)
                    j6++;
                continue;
            }
            ac1[0] = s1.charAt(i2);
            if((ac1[0] & 0xff00) == 0 && ac1[0] != '\327' && ac1[0] != '\367')
            {
                if(!flag)
                {
                    g1.setColor(Color.gray);
                    g1.drawString(new String(ac1), l5 + 1 + 1, i6 + 17);
                    g1.setColor(Color.black);
                    g1.drawString(new String(ac1), l5 + 1, i6 + 17);
                }
                l5 += 10;
                i8++;
            } else
            {
                if(!flag)
                {
                    g1.setColor(Color.gray);
                    g1.drawString(new String(ac1), l5 + 2 + 1, i6 + 16);
                    g1.setColor(Color.black);
                    g1.drawString(new String(ac1), l5 + 2, i6 + 16);
                }
                l5 += 20;
                i8 += 2;
            }
            if(i8 < k1 * 2 || i2 < j8 - 1 && (s1.charAt(i2 + 1) == '\u3002' || s1.charAt(i2 + 1) == '\u3001' || s1.charAt(i2 + 1) == '\n'))
                continue;
            l5 = i1;
            i6 += 22;
            i8 = 0;
            if(i2 < j8 - 1)
                j6++;
        }

        return j6;
    }

    public void a(Graphics g1, String s1, int i1, int j1, int k1, int l1, boolean flag)
    {
        g1.setColor(new Color(96, 96, 96));
        g1.fillRoundRect(i1 - 2, j1 - 2, k1 + 4, l1 + 4, 30, 30);
        g1.setColor(Color.white);
        g1.fillRoundRect(i1, j1, k1, l1, 30, 30);
        a(g1, s1, i1 + 7, j1 + 9, 16, false, flag, false);
        if(bL)
        {
            g1.drawImage(bo[dC], bJ + 2, dh + 2, this);
            g1.drawImage(bo[dD], bJ + 2 + 40, dh + 2, this);
        }
    }

    public boolean a(Graphics g1, Graphics g2, String s1, int i1, int j1)
    {
        char c1 = '\u0154';
        int k1 = 174;
        int l1 = a(g1, s1, 0, 0, 16, true, true, true);
        if(l1 == 0)
            return false;
        k1 = 22 * l1 + 20;
        if(bL)
            k1 += 40;
        if(j1 == -3)
            j1 = 220 - k1 / 2;
        if(j1 == -2)
        {
            j1 = (110 - k1 / 2) + 10;
            if(j1 < 20)
                j1 = 20;
        }
        if(j1 == -1)
        {
            j1 = 330 - k1 / 2 - 10;
            if(j1 + k1 > 420)
                j1 = 420 - k1;
        }
        dh = j1 + 22 * l1 + 8;
        if(g2 != null)
        {
            a(g2, s1, i1, j1, c1, k1, true);
            a(g2, false);
            a(g1, s1, i1, j1, c1, k1, false);
        } else
        {
            a(g1, s1, i1, j1, c1, k1, false);
        }
        return true;
    }

    public void e(int i1)
    {
        try
        {
            Thread.sleep(i1);
        }
        catch(InterruptedException interruptedexception)
        {
            System.err.println("110 Thread Error!");
        }
    }

    public void a(int i1, Graphics g1)
    {
        a(g1, true, 0, 0);
        e(i1);
    }

    public void b(String s1)
    {
        try
        {
            InputStream inputstream = (new URL(getDocumentBase(), s1)).openStream();
            int k1 = 0;
            do
            {
                int l1;
                if((l1 = inputstream.read(cZ, k1, 10000)) == -1)
                    break;
                k1 += l1;
                if(k1 + 10000 >= dk * 65000)
                {
                    byte abyte0[] = new byte[k1];
                    for(int i1 = 0; i1 < k1; i1++)
                        abyte0[i1] = cZ[i1];

                    dk++;
                    cZ = new byte[dk * 65000];
                    int j1 = 0;
                    while(j1 < k1) 
                    {
                        cZ[j1] = abyte0[j1];
                        j1++;
                    }
                }
            } while(true);
            dl = k1;
            inputstream.close();
        }
        catch(MalformedURLException malformedurlexception)
        {
            bV = true;
            System.err.println("120 URL Error!");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            bS = true;
            System.err.println("121 File not Found!");
        }
        catch(IOException ioexception)
        {
            bV = true;
            System.err.println("122 MapRead Error!");
        }
        catch(SecurityException securityexception)
        {
            bV = true;
            System.err.println("123 MapRead Error!");
        }
        catch(Exception exception)
        {
            bV = true;
            System.err.println("124 MapRead Error!");
        }
    }

    public void a(Graphics g1, String s1, boolean flag)
    {
        int l7 = 0;
        if(flag)
        {
            b(s1);
            if(bS || bV)
                return;
            dj = dk;
            cY = new byte[dj * 65000];
        }
        d();
        l7 = dm;
        int k8 = a(cY[2]);
        int i8;
        int j8;
        if(k8 <= 29)
        {
            aw = 3;
            ax = 4;
            ay = 5;
            az = 6;
            aA = 18;
            aB = 19;
            aC = 20;
            ej = a(cY[aA]);
            ek = a(cY[aB]);
            i8 = a(cY[ay]);
            j8 = a(cY[az]);
            cF = a(cY[aw]);
            cG = a(cY[ax]);
            dg = 1;
        } else
        {
            aw = 34;
            ax = 36;
            ay = 38;
            az = 40;
            aA = 42;
            aB = 44;
            aC = 60;
            ej = a(cY[aA]) + a(cY[aA + 1]) * 256;
            ek = a(cY[aB]) + a(cY[aB + 1]) * 256;
            i8 = a(cY[ay]) + a(cY[ay + 1]) * 256;
            j8 = a(cY[az]) + a(cY[az + 1]) * 256;
            cF = a(cY[aw]) + a(cY[aw + 1]) * 256;
            cG = a(cY[ax]) + a(cY[ax + 1]) * 256;
        }
        ed = a(cY[32]) + a(cY[33]) * 256;
        ec = a(cY[10]) + a(cY[11]) * 256;
        ee = a(cY[12]) + a(cY[13]) * 256;
        ef = a(cY[14]) + a(cY[15]) * 256;
        eg = a(cY[16]) + a(cY[17]) * 256;
        for(int i1 = 0; i1 < 12; i1++)
            eh[i1] = a(cY[aC + i1]);

        cD = a(cY[46]) + a(cY[47]) * 256;
        cH = a(cY[48]) + a(cY[49]) * 256;
        if(k8 < 28)
            cD = 71;
        else
        if(k8 <= 29)
            cD = 101;
        a(i8, j8);
        di = 100;
        if(k8 >= 29)
            di = 90;
        if(flag)
        {
            dY = new short[cD][cD];
            dZ = new short[cD + 1][cD + 1];
            db = new short[cD][cD];
            dc = new short[cD + 1][cD + 1];
            if(a)
            {
                de = new int[cF + 1][40];
                dd = new int[cG + 1][40];
            }
        }
        for(int l4 = 0; l4 < cD; l4++)
        {
            for(int i6 = 0; i6 < cD; i6++)
            {
                if(k8 <= 29)
                {
                    dY[l4][i6] = (short)a(cY[di]);
                    di++;
                } else
                {
                    dY[l4][i6] = (short)(a(cY[di]) + a(cY[di + 1]) * 256);
                    di += 2;
                }
                if(dY[l4][i6] >= cF)
                    dY[l4][i6] = 0;
            }

        }

        for(int i5 = 0; i5 < cD; i5++)
        {
            for(int j6 = 0; j6 < cD; j6++)
            {
                if(k8 <= 29)
                {
                    dZ[i5][j6] = (short)a(cY[di]);
                    di++;
                } else
                {
                    dZ[i5][j6] = (short)(a(cY[di]) + a(cY[di + 1]) * 256);
                    di += 2;
                }
                if(dZ[i5][j6] >= cG)
                    dZ[i5][j6] = 0;
            }

        }

        int j7;
        int k7;
        if(k8 <= 29)
        {
            j7 = 40;
            k7 = 40;
        } else
        {
            j7 = 60;
            k7 = 60;
        }
        bc = j7;
        bd = k7;
        if(flag)
            eb = new int[cF][60];
        for(int j1 = 0; j1 < cF; j1++)
        {
            for(int l3 = 0; l3 < j7; l3++)
                if(l3 == 1 || l3 == 2)
                {
                    if(flag)
                        eb[j1][l3] = 0;
                    di += 2;
                } else
                {
                    eb[j1][l3] = a(cY[di]) + a(cY[di + 1]) * 256;
                    di += 2;
                }

        }

        if(flag)
            ea = new int[cG][60];
        for(int k1 = 0; k1 < cG; k1++)
        {
            for(int i4 = 0; i4 < k7; i4++)
                if(i4 == 1 || i4 == 2)
                {
                    if(flag)
                        ea[k1][i4] = 0;
                    di += 2;
                } else
                {
                    ea[k1][i4] = a(cY[di]) + a(cY[di + 1]) * 256;
                    di += 2;
                }

        }

        if(k8 <= 29)
        {
            for(int j4 = 0; j4 < cF; j4++)
            {
                for(int l1 = 9; l1 >= 0; l1--)
                {
                    int i9 = eb[j4][20 + l1 * 2];
                    i9 &= 0xff;
                    int k9 = eb[j4][20 + l1 * 2];
                    k9 >>= 8;
                    int j5 = eb[j4][20 + l1 * 2 + 1];
                    j5 &= 0xff;
                    int k6 = eb[j4][20 + l1 * 2 + 1];
                    k6 >>= 8;
                    if(j5 == 250)
                        j5 = 9000;
                    else
                    if(j5 > 100)
                        j5 += 9840;
                    if(k6 == 250)
                        k6 = 9000;
                    else
                    if(k6 > 100)
                        k6 += 9840;
                    eb[j4][20 + l1 * 4] = i9;
                    eb[j4][20 + l1 * 4 + 3] = k9;
                    eb[j4][20 + l1 * 4 + 1] = j5;
                    eb[j4][20 + l1 * 4 + 2] = k6;
                }

                if(eb[j4][3] != 2)
                    continue;
                if(eb[j4][16] > 100)
                    eb[j4][16] += 9840;
                if(eb[j4][17] > 100)
                    eb[j4][17] += 9840;
            }

            for(int k4 = 0; k4 < cG; k4++)
            {
                for(int i2 = 9; i2 >= 0; i2--)
                {
                    int j9 = ea[k4][20 + i2 * 2];
                    j9 &= 0xff;
                    int l9 = ea[k4][20 + i2 * 2];
                    l9 >>= 8;
                    int k5 = ea[k4][20 + i2 * 2 + 1];
                    k5 &= 0xff;
                    int l6 = ea[k4][20 + i2 * 2 + 1];
                    l6 >>= 8;
                    if(k5 == 250)
                        k5 = 9000;
                    else
                    if(k5 > 100)
                        k5 += 9840;
                    if(l6 == 250)
                        l6 = 9000;
                    else
                    if(l6 > 100)
                        l6 += 9840;
                    ea[k4][20 + i2 * 4] = j9;
                    ea[k4][20 + i2 * 4 + 3] = l9;
                    ea[k4][20 + i2 * 4 + 1] = k5;
                    ea[k4][20 + i2 * 4 + 2] = l6;
                }

                if(ea[k4][3] != 18)
                    continue;
                if(ea[k4][16] > 100)
                    ea[k4][16] += 9840;
                if(ea[k4][17] > 100)
                    ea[k4][17] += 9840;
            }

        }
        for(int l5 = 0; l5 < cD; l5++)
        {
            for(int i7 = 0; i7 < cD; i7++)
                b(l5, i7);

        }

        di = l7;
        for(int j2 = 0; j2 < dl; j2++)
            cY[j2] = cZ[j2];

        if(k8 >= 30)
            bj = c();
        if(k8 <= 29)
            cH = 400;
        dR = new String[cH];
        for(int k2 = 0; k2 < cH; k2++)
            dR[k2] = c();

        bl = c();
        if(k8 <= 29)
            bj = c();
        else
            c();
        if(k8 >= 29)
            bk = (a(bj) / 10 - 1197) / 17 - 2357;
        else
            bk = a(bj);
        bi = c();
        bh = c();
        if(k8 >= 30)
        {
            for(int l2 = 0; l2 < 20; l2++)
                dS[l2] = c();

        } else
        {
            for(int i3 = 0; i3 < 20; i3++)
                dS[i3] = "";

        }
        f(0);
        if(a && (bk >= 1000 || k8 <= 30))
            cb = true;
        if(getParameter("Id") != null)
        {
            int l8 = a(getParameter("Energy")) * 7;
            l8 += a(getParameter("Strength")) * 11;
            l8 += a(getParameter("Defence")) * 19;
            l8 += a(getParameter("Gold")) * 5;
            l8 += a(getParameter("PlayerX")) * 17;
            l8 += a(getParameter("PlayerY")) * 21;
            for(int j3 = 0; j3 < 12; j3++)
                if(getParameter("item" + (j3 + 1)) != null)
                    l8 += a(getParameter("item" + (j3 + 1))) * 17;

            l8 %= 9999;
            if(a(bj) != 0)
                l8 *= bk;
            l8 %= 9999;
            if(l8 == a(getParameter("Id")))
            {
                ec = a(getParameter("Energy"));
                ee = a(getParameter("Strength"));
                ef = a(getParameter("Defence"));
                eg = a(getParameter("Gold"));
                for(int k3 = 0; k3 < 12; k3++)
                    if(getParameter("item" + (k3 + 1)) != null)
                        eh[k3] = a(getParameter("item" + (k3 + 1)));

                f(0);
                ee -= cA;
                ef -= cB;
                if(getParameter("Flag") != null && (getParameter("Flag").equals("ON") || getParameter("Flag").equals("on")))
                {
                    be = a(getParameter("PlayerX"));
                    dW = be / 10;
                    be = (be % 10) * 5;
                    bf = a(getParameter("PlayerY"));
                    dX = bf / 10;
                    bf = (bf % 10) * 5;
                }
            }
        }
    }

    public int a(byte byte0)
    {
        int i1 = byte0;
        if(i1 < 0)
            i1 += 256;
        return i1;
    }

    public String c()
    {
        String s1 = new String();
        byte abyte0[] = new byte[1];
        int i1;
        for(i1 = 0; i1 < 1000 && (cY[di + i1 * 2] != 0 || cY[di + i1 * 2 + 1] != 0); i1++)
        {
            abyte0[0] = cY[di + i1 * 2];
            byte byte0 = cY[di + i1 * 2 + 1];
            s1 = s1 + new String(abyte0, byte0);
        }

        di += i1 * 2 + 2;
        return s1;
    }

    public void d()
    {
        int j2 = 0;
        dn = false;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        for(; cZ[i1] != 0 || cZ[i1 + 1] != 0 || cZ[i1 + 2] != 0; i1++)
        {
            cY[j1] = cZ[i1];
            j1++;
            if(cZ[i1] == cZ[i1 + 1])
            {
                int i2 = a(cZ[i1 + 2]);
                for(int l1 = 0; l1 < i2; l1++)
                {
                    cY[j1] = cZ[i1];
                    j1++;
                }

                i1 += 2;
            }
            if(j1 + 255 < dj * 65000)
                continue;
            dj++;
            cY = new byte[dj * 65000];
            d();
            if(dn)
                return;
        }

        System.out.println("ExtractData = " + j1 + " " + i1);
        if(a(cY[2]) >= 29)
        {
            for(int k1 = 2; k1 < j1; k1++)
                j2 += cY[k1] * (k1 % 8 + 1);

            j2 = j2 % 0x10000 & 0xffff;
            int k2 = a(cY[0]) + a(cY[1]) * 256;
            if(k2 != j2)
                bU = true;
            System.out.println("buff = " + j2 + " " + k2);
        }
        dn = true;
        dm = i1 + 3;
    }

    public void b(int i1, int j1)
    {
        if(ea[dZ[i1][j1]][3] == 16)
        {
            int k1 = dq.nextInt() % 10;
            if(k1 < 0)
                k1 = -k1;
            dZ[i1][j1] = (short)ea[dZ[i1][j1]][10 + k1];
            if(dZ[i1][j1] >= cG)
                dZ[i1][j1] = 0;
        }
    }

    public void h(Graphics g1)
    {
        int l2 = 4;
        bn = getImage(getDocumentBase(), bh);
        bm.addImage(bn, 0);
        try
        {
            bm.waitForID(0);
        }
        catch(InterruptedException interruptedexception)
        {
            bT = true;
            System.err.println("100 Tracker Error!");
            return;
        }
        catch(Exception exception)
        {
            bT = true;
            System.err.println("101 waitForID Error!");
            return;
        }
        a(g1, 3);
        if((bm.statusAll(false) & 4) != 0)
        {
            bT = true;
            return;
        }
        int i3 = bn.getHeight(this);
        for(int i1 = 0; i1 < cF; i1++)
        {
            int l1 = eb[i1][6] / 40;
            int j2 = eb[i1][7] / 40;
            if(j2 >= (i3 + 39) / 40)
            {
                eb[i1][1] = 0;
                continue;
            }
            if(j2 > l2)
                l2 = j2;
            eb[i1][1] = l1 + j2 * 10;
        }

        for(int j1 = 0; j1 < cG; j1++)
        {
            int i2 = ea[j1][6] / 40;
            int k2 = ea[j1][7] / 40;
            if(k2 >= (i3 + 39) / 40)
            {
                ea[j1][1] = 0;
            } else
            {
                if(k2 > l2)
                    l2 = k2;
                ea[j1][1] = i2 + k2 * 10;
            }
            i2 = ea[j1][8] / 40;
            k2 = ea[j1][9] / 40;
            if(k2 >= (i3 + 39) / 40)
            {
                ea[j1][2] = 0;
                continue;
            }
            if(k2 > l2)
                l2 = k2;
            ea[j1][2] = i2 + k2 * 10;
        }

        if(i3 != -1)
            cE = ((i3 - 1) / 40 + 1) * 10;
        else
            cE = (l2 + 1) * 10;
        bo = new Image[cE];
        for(bp = 0; bp < cE; bp++)
            bo[bp] = createImage(new FilteredImageSource(bn.getSource(), new CropImageFilter((bp % 10) * 40, (bp / 10) * 40, 40, 40)));

        for(int k1 = 0; k1 < bp; k1++)
            bm.addImage(bo[k1], 0);

        try
        {
            bm.waitForID(0);
        }
        catch(InterruptedException interruptedexception1)
        {
            System.err.println("102 Tracker Error!");
        }
        a(g1, 4);
        bn.flush();
        System.out.println("Crop ID = " + bp + " " + i3);
    }

    public void e()
    {
        Font font = new Font("TimesRoman", 0, 20);
        FontMetrics fontmetrics = getFontMetrics(font);
        int i1 = fontmetrics.getAscent();
        if(i1 > 18)
            ca = true;
        System.out.println("Font = " + i1);
    }

    public void a(Graphics g1, String s1, int i1, int j1, int k1, int l1, int i2)
    {
        Font font;
        if(ca)
            font = new Font("TimesRoman", 0, i1);
        else
            font = new Font("TimesRoman", 1, i1);
        FontMetrics fontmetrics = getFontMetrics(font);
        g1.setFont(font);
        int l2 = fontmetrics.stringWidth(s1);
        int i3 = fontmetrics.getAscent();
        if(i3 >= 18)
        {
            i3 -= 6;
            if(!ca)
                i3++;
        } else
        if(i3 >= 16)
            i3 -= 4;
        else
        if(i3 >= 12)
            i3 -= 2;
        if(!ca)
            i3++;
        int j2 = j1 + (l1 - l2) / 2;
        int k2 = k1 + (i2 + i3) / 2;
        g1.setColor(new Color(160, 128, 96));
        g1.drawString(s1, j2 + 1, k2);
        g1.drawString(s1, j2 + 1, k2 + 1);
        g1.setColor(Color.black);
        g1.drawString(s1, j2, k2);
    }

    public void a(int i1, String s1, int j1, int k1, int l1, int i2, int j2)
    {
        if(bH == i1)
        {
            bC.drawImage(bz, 0, l1, this);
            a(bC, s1, j1, k1 + 3, l1 + 3, i2, j2);
        } else
        {
            a(bC, s1, j1, k1, l1, i2, j2);
        }
    }

    public void a(Graphics g1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        int ai1[] = new int[4];
        if(!flag)
        {
            g1.drawImage(bv, 440, 0, this);
            return;
        }
        if(flag3)
        {
            Font font = new Font("TimesRoman", 1, 18);
            bC.setFont(font);
            bC.setColor(Color.gray);
            bC.fillRect(0, 0, 120, 440);
            for(int i1 = 0; i1 < 4; i1++)
            {
                for(int i2 = 0; i2 < 3; i2++)
                    bC.drawImage(bo[i2 + dL], i2 * 40, 300 + i1 * 35, this);

            }

            bG.setColor(Color.black);
            bG.fillRect(0, 0, 120, 35);
            for(int j2 = 0; j2 < 3; j2++)
                bG.drawImage(bo[j2 + dL], j2 * 40 + 3, 3, this);

            if(bQ)
                a(11, "Quick Load", 18, 0, 300, 120, 35);
            else
                a(14, "Password", 18, 0, 300, 120, 35);
            a(10, "Quick Save", 18, 0, 335, 120, 35);
            a(5, "Restart Game", 16, 0, 370, 120, 35);
            a(3, "Goto MSP", 18, 0, 405, 120, 35);
        }
        if(flag1)
        {
            if(ed != 0 && ec > ed)
                ec = ed;
            for(int j1 = 0; j1 < 4; j1++)
                if(dA[j1] == 0 || ex == 0)
                {
                    for(int k2 = 0; k2 < 3; k2++)
                        bC.drawImage(bo[k2 + dL], k2 * 40, j1 * 35, this);

                } else
                {
                    bC.drawImage(bz, 0, j1 * 35, this);
                }

            for(int k1 = 0; k1 < 4; k1++)
                if(dA[k1] > 0 || ex == 0)
                    ai1[k1] = 3;
                else
                    ai1[k1] = 0;

            bC.drawImage(bo[dG], 6 + ai1[0], 0 + ai1[0], this);
            bC.drawImage(bo[dH], 6 + ai1[1], 35 + ai1[1], this);
            bC.drawImage(bo[dI], 6 + ai1[2], 70 + ai1[2], this);
            bC.drawImage(bo[dJ], 6 + ai1[3], 105 + ai1[3], this);
            if(ec < 0)
                ec = 0;
            a(bC, String.valueOf(ec), 22, 38 + ai1[0], 0 + ai1[0], 82, 35);
            a(bC, String.valueOf(ee + cA), 22, 38 + ai1[1], 35 + ai1[1], 82, 35);
            a(bC, String.valueOf(ef + cB), 22, 38 + ai1[2], 70 + ai1[2], 82, 35);
            a(bC, String.valueOf(eg), 22, 38 + ai1[3], 105 + ai1[3], 82, 35);
        }
        if(flag2)
        {
            for(int l1 = 0; l1 < 4; l1++)
            {
                for(int l2 = 0; l2 < 3; l2++)
                {
                    int k3 = l1 * 3 + l2;
                    bC.drawImage(bo[dN], l2 * 40, 140 + l1 * 40, this);
                    if(dw >= 1 && dw <= 15 + dz && du == k3 && dv == 0 || eh[k3] == 0 && (k3 != cy || cz <= 0))
                        continue;
                    int j3;
                    if(k3 == cy && cz > 0)
                        j3 = ea[cz][1];
                    else
                        j3 = ea[eh[k3]][1];
                    if(du == k3 && dv != 0 && dw != 0)
                        j3 = ea[dv][1];
                    if(ea[eh[k3]][4] == 0 && k3 != cy)
                    {
                        bC.drawImage(bo[j3], l2 * 40, 140 + l1 * 40, this);
                        continue;
                    }
                    byte byte0 = 0;
                    if(cz > 0 && k3 == cy)
                    {
                        bB.setColor(Color.black);
                        bB.fillRect(0, 0, 40, 40);
                        byte0 = 3;
                    }
                    bB.drawImage(bo[dN], byte0, byte0, this);
                    if(dV != 0)
                    {
                        bB.drawImage(bo[dV], byte0, byte0, this);
                    } else
                    {
                        for(int i3 = 0; i3 < 5; i3++)
                        {
                            if(i3 % 2 == 1)
                                bB.setColor(Color.white);
                            else
                                bB.setColor(Color.red);
                            bB.drawRect(i3 + 1 + byte0, i3 + 1 + byte0, 37 - i3 * 2, 37 - i3 * 2);
                        }

                    }
                    bB.drawImage(bo[j3], byte0, byte0, this);
                    bC.drawImage(bu, l2 * 40, 140 + l1 * 40, this);
                }

            }

        }
        g1.drawImage(bv, 440, 0, this);
    }

    public void f(int i1)
    {
        int j1;
        for(j1 = 0; j1 < 12 && eh[j1] != 0; j1++);
        if(j1 == 12 && i1 != 0 && ea[i1][15] == 0)
            return;
        if(i1 != 0)
            if(ea[i1][15] != 0)
            {
                int j2 = eh[ea[i1][15] - 1];
                eh[ea[i1][15] - 1] = i1;
                if(ea[j2][15] != ea[i1][15])
                {
                    int k1 = 0;
                    do
                    {
                        if(k1 >= 12)
                            break;
                        if(eh[k1] == 0)
                        {
                            eh[k1] = j2;
                            break;
                        }
                        k1++;
                    } while(true);
                }
            } else
            {
                int l1 = 0;
                do
                {
                    if(l1 >= 12)
                        break;
                    if(eh[l1] == 0)
                    {
                        eh[l1] = i1;
                        break;
                    }
                    l1++;
                } while(true);
            }
        cA = 0;
        cB = 0;
        for(int i2 = 0; i2 < 12; i2++)
            if(eh[i2] != 0)
            {
                cA += ea[eh[i2]][11];
                cB += ea[eh[i2]][12];
            }

    }

    public void b(Graphics g1, int i1, int j1)
    {
        short word0 = dZ[j1][i1];
        int k1 = (be / 5) * 40;
        int l1 = (bf / 5) * 40;
        if(cP)
        {
            if(bs && cQ <= 40 && (cQ == 0 || el > 10))
                a(3);
            bp = eb[dY[dP][dO]][1];
            bB.drawImage(bo[bp], 0, 0, this);
            if(eE == 0)
                bB.drawImage(bo[bq], 0, 0, this);
            a(bB, k1 / 40, l1 / 40, 0, 0);
            g1.drawImage(bu, k1, l1, this);
            bp = eb[dY[j1][i1]][1];
            bB.drawImage(bo[bp], 0, 0, this);
            a(g1, i1, j1, true);
            g1.drawImage(bu, (i1 - dW * 10) * 40, (j1 - dX * 10) * 40, this);
            if(ee + cA > cK || ef + cB < cJ || !bY)
            {
                if(cI > (ee + cA) - cK)
                {
                    if(ee + cA > cK)
                        cI = cI - ((ee + cA) - cK);
                } else
                {
                    cI = 0;
                    co = dZ[j1][i1];
                    ct = true;
                    cu = false;
                    cl = 200;
                    ep = true;
                    dT = ea[co][5];
                    b(g1, i1, j1, 0);
                    eg += cL;
                    cv = true;
                    if(dZ[j1][i1] == co)
                    {
                        dZ[j1][i1] = (short)ea[co][14];
                        c(g1, i1, j1);
                    }
                    cO = false;
                    cw = 1;
                    if(ea[co][19] != 0 && bs)
                    {
                        cl = 0;
                        e(200);
                        a(ea[co][19]);
                    }
                }
            } else
            {
                ct = true;
                cu = false;
                ep = true;
                dT = 1;
                dR[dT] = "勝てねーよバーカ！";
                cO = false;
                cw = 1;
                cl = 200;
            }
            b(g1, word0);
            cP = false;
            if(cQ > 40 || el <= 10)
                e(20);
            else
                e(120);
            cQ++;
        } else
        if(!cP)
        {
            bp = eb[dY[j1][i1]][1];
            bB.drawImage(bo[bp], 0, 0, this);
            a(g1, i1, j1, false);
            g1.drawImage(bu, (i1 - dW * 10) * 40, (j1 - dX * 10) * 40, this);
            bp = eb[dY[dP][dO]][1];
            bB.drawImage(bo[bp], 0, 0, this);
            if(eE == 0)
                bB.drawImage(bo[bq], 0, 0, this);
            bB.drawImage(bo[dK], 0, 0, this);
            a(bB, k1 / 40, l1 / 40, 0, 0);
            g1.drawImage(bu, k1, l1, this);
            if(cJ > ef + cB)
                if(ec > cJ - ef - cB)
                {
                    ec -= cJ - ef - cB;
                } else
                {
                    a(ej, ek);
                    ec = 0;
                    ct = true;
                    cu = true;
                    cO = false;
                    e(800);
                }
            cP = true;
            if(cQ > 40 || el <= 10)
                e(20);
            else
                e(120);
            cQ++;
        }
        a(g1, true, true, true, false);
    }

    public void a(Graphics g1, int i1, int j1, boolean flag)
    {
        bp = ea[dZ[j1][i1]][1];
        int k1 = (i1 - dW * 10) * 40;
        int l1 = (j1 - dX * 10) * 40;
        bB.drawImage(bo[bp], 0, 0, this);
        if(flag)
            bB.drawImage(bo[dK], 0, 0, this);
        a(bB, k1 / 40, l1 / 40, 0, 0);
    }

    public void b(Graphics g1, int i1)
    {
        char c1;
        if(bf / 5 >= 5)
            c1 = 'P';
        else
            c1 = '\u012C';
        bF.setColor(new Color(96, 96, 96));
        bF.fillRect(0, 0, 340, 60);
        bF.setColor(Color.white);
        bF.fillRoundRect(2, 2, 336, 56, 20, 20);
        Font font = new Font("Courier", 0, 18);
        bF.setFont(font);
        bp = ea[i1][1];
        bF.drawImage(bo[bp], 20, 10, this);
        a(bF, "Lv  " + cI, Color.black, Color.gray, 80, 26);
        a(bF, "HP  " + cJ + "  MP  " + cK, Color.black, Color.gray, 80, 46);
        g1.drawImage(by, 50, c1, this);
    }

    public void c(Graphics g1, int i1, int j1)
    {
        b(j1, i1);
        if(i1 < dW * 10 || i1 >= dW * 10 + 11 || j1 < dX * 10 || j1 >= dX * 10 + 11)
            return;
        if(ct)
            return;
        if(dB)
            return;
        bB.setColor(Color.gray);
        bB.fillRect(0, 0, 40, 40);
        short word0 = dY[j1][i1];
        bp = eb[word0][1];
        bB.drawImage(bo[bp], 0, 0, this);
        if(eE == 0 && i1 == be / 5 + dW * 10 && j1 == bf / 5 + dX * 10)
            bB.drawImage(bo[bq], 0, 0, this);
        int k1 = (i1 % 10) * 40;
        if(i1 / 10 != dW)
            k1 = 400;
        int l1 = (j1 % 10) * 40;
        if(j1 / 10 != dX)
            l1 = 400;
        word0 = dZ[j1][i1];
        if(word0 != 0 && !a(word0, i1, dO, j1, dP))
        {
            bp = ea[word0][1];
            bB.drawImage(bo[bp], 0, 0, this);
        }
        a(bB, k1 / 40, l1 / 40, 0, 0);
        g1.drawImage(bu, k1, l1, this);
    }

    public void i(Graphics g1)
    {
        Font font = new Font("Monospaced", 1, 22);
        g1.setFont(font);
        g1.setColor(Color.white);
        g1.fillRoundRect(80, 30, 280, 40, 8, 8);
        g1.setColor(Color.black);
        g1.drawString("Score", 120, 58);
        g1.drawString(String.valueOf(cC), 240, 58);
    }

    public void b(Graphics g1, int i1, int j1, int k1)
    {
        b(g1, i1, j1, k1, 0);
    }

    public void b(Graphics g1, int i1, int j1, int k1, int l1)
    {
        byte byte0 = 0;
        byte byte1 = 10;
        int j3;
        boolean flag;
        if(k1 == 1)
        {
            j3 = dY[j1][i1];
            flag = false;
        } else
        if(k1 == 2)
        {
            j3 = eh[cy];
            flag = true;
        } else
        {
            j3 = dZ[j1][i1];
            flag = true;
        }
        if(l1 != 0)
            j3 = l1;
        if(k1 == 3)
            byte1 = 5;
        else
        if(k1 == 4)
        {
            byte0 = 5;
            byte1 = 10;
        }
        for(int i2 = byte0; i2 < byte1; i2++)
        {
            int j2;
            if(flag)
                j2 = ea[j3][20 + i2 * 4];
            else
                j2 = eb[j3][20 + i2 * 4];
            int k2;
            if(flag)
                k2 = ea[j3][20 + i2 * 4 + 3];
            else
                k2 = eb[j3][20 + i2 * 4 + 3];
            int l2;
            if(flag)
                l2 = ea[j3][20 + i2 * 4 + 1];
            else
                l2 = eb[j3][20 + i2 * 4 + 1];
            if(l2 == 9000)
                l2 = dO;
            else
            if(l2 > 9000)
                l2 = (l2 - 10000) + i1;
            int i3;
            if(flag)
                i3 = ea[j3][20 + i2 * 4 + 2];
            else
                i3 = eb[j3][20 + i2 * 4 + 2];
            if(i3 == 9000)
                i3 = dP;
            else
            if(i3 > 9000)
                i3 = (i3 - 10000) + j1;
            if(l2 == 0 && i3 == 0 || l2 < 0 || l2 > cD - 1 || i3 < 0 || i3 > cD - 1)
                continue;
            if(k2 == 0)
            {
                if(j2 < cG)
                {
                    dZ[i3][l2] = (short)j2;
                    c(g1, l2, i3);
                }
                continue;
            }
            if(k2 == 1 && j2 < cF)
            {
                dY[i3][l2] = (short)j2;
                c(g1, l2, i3);
            }
        }

    }

    public void a(String s1, boolean flag)
    {
        String s3 = "";
        boolean flag1 = false;
        String s4 = URLEncoder.encode(bl);
        StringTokenizer stringtokenizer = new StringTokenizer(s1);
        String s2 = stringtokenizer.nextToken();
        if(stringtokenizer.hasMoreTokens())
            s3 = stringtokenizer.nextToken();
        if(flag && !dR[5].equals("BLANK"))
        {
            bH = 4;
            bL = true;
            return;
        }
        String s5 = new String(s2.toCharArray(), s2.length() - 3, 3);
        int i2 = ec * 7;
        i2 += (ee + cA) * 11;
        i2 += (ef + cB) * 19;
        i2 += eg * 5;
        i2 += dO * 17;
        i2 += dP * 21;
        for(int i1 = 0; i1 < 12; i1++)
            i2 += eh[i1] * 17;

        i2 %= 9999;
        if(a(bj) != 0)
            i2 *= bk;
        i2 %= 9999;
        int j2 = en * 231;
        for(int j1 = 0; j1 < 100; j1++)
            j2 += ei[j1] * (j1 + 5);

        if(a(bj) != 0)
            j2 *= bk;
        j2 = i2 % 9999;
        if(s3.equals("EXPAND"))
        {
            s2 = s2 + "&";
            flag1 = true;
        } else
        if(s5.equals("cgi"))
        {
            s2 = s2 + "?";
            flag1 = true;
        }
        if(flag1)
        {
            eL = s2;
            s2 = s2 + "HP=" + ec + "&AT=" + (ee + cA) + "&DF=" + (ef + cB) + "&GD=" + eg + "&PX=" + dO + "&PY=" + dP + "&ID=" + i2 + "&WNAME=" + s4;
            for(int k1 = 0; k1 < 12; k1++)
                s2 = s2 + "&ITEM" + (k1 + 1) + "=" + eh[k1];

            if(!s3.equals("EXPAND"))
                s2 = s2 + "&FLAG=ON" + "&DAT=" + getParameter("paramMapName");
            s2 = s2 + "&STEP=" + en + "&IDB=" + j2;
            for(int l1 = 0; l1 < 100; l1++)
                s2 = s2 + "&V" + l1 + "=" + ei[l1];

        }
        System.out.println(s2);
        if(bP)
            System.gc();
        try
        {
            bO = true;
            bP = true;
            if(s3.equals("BLANK") || s3.equals("EXPAND"))
            {
                URL url = new URL(getDocumentBase(), s2);
                getAppletContext().showDocument(url);
            } else
            {
                if(cW)
                    s3 = "wwawindow";
                if(s3.equals(""))
                {
                    URL url1 = new URL(getDocumentBase(), s2);
                    getAppletContext().showDocument(url1);
                } else
                {
                    URL url2 = new URL(getDocumentBase(), s2);
                    getAppletContext().showDocument(url2, s3);
                }
            }
        }
        catch(MalformedURLException malformedurlexception)
        {
            System.err.println("140 URL Error!");
        }
    }

    public void b(String s1, boolean flag)
    {
        ep = true;
        dT = 1;
        dR[dT] = s1;
        bZ = flag;
    }

    public void f()
    {
        int j3 = 0;
        da[j3] = be;
        da[++j3] = dW;
        da[++j3] = bf;
        da[++j3] = dX;
        da[++j3] = ec;
        da[++j3] = ee;
        da[++j3] = ef;
        da[++j3] = eg;
        da[++j3] = ej;
        da[++j3] = ek;
        da[++j3] = cA;
        da[++j3] = cB;
        da[++j3] = dU;
        da[++j3] = dC;
        da[++j3] = ev;
        da[++j3] = ew;
        da[++j3] = dg;
        da[++j3] = en;
        da[++j3] = eo;
        da[++j3] = ed;
        for(int i1 = 0; i1 < 12; i1++)
            da[++j3] = eh[i1];

        da[++j3] = dG;
        da[++j3] = dH;
        da[++j3] = dI;
        da[++j3] = dJ;
        da[++j3] = dK;
        da[++j3] = dL;
        da[++j3] = dN;
        da[++j3] = dM;
        da[++j3] = eE;
        da[++j3] = dV;
        da[++j3] = dr;
        da[++j3] = ex;
        for(int j1 = 0; j1 < 4; j1++)
            da[++j3] = ds[j1];

        for(int k1 = 0; k1 < 100; k1++)
            da[++j3] = ei[k1];

        for(int l2 = 0; l2 < cD; l2++)
        {
            for(int i3 = 0; i3 < cD; i3++)
            {
                db[l2][i3] = dY[l2][i3];
                dc[l2][i3] = dZ[l2][i3];
            }

        }

        if(a)
        {
            for(int l1 = 0; l1 < cF; l1++)
            {
                for(int j2 = 0; j2 < 40; j2++)
                    de[l1][j2] = eb[l1][j2];

            }

            for(int i2 = 0; i2 < cG; i2++)
            {
                for(int k2 = 0; k2 < 40; k2++)
                    dd[i2][k2] = ea[i2][k2];

            }

        }
    }

    public void g()
    {
        int j3 = 0;
        a(99);
        cS = true;
        be = da[j3];
        be = be - be % 5;
        dW = da[++j3];
        bf = da[++j3];
        bf = bf - bf % 5;
        dX = da[++j3];
        ec = da[++j3];
        ee = da[++j3];
        ef = da[++j3];
        eg = da[++j3];
        ej = da[++j3];
        ek = da[++j3];
        cA = da[++j3];
        cB = da[++j3];
        dU = da[++j3];
        g(da[++j3]);
        ev = da[++j3];
        ew = da[++j3];
        dg = da[++j3];
        en = da[++j3];
        eo = da[++j3];
        ed = da[++j3];
        for(int i1 = 0; i1 < 12; i1++)
            eh[i1] = da[++j3];

        dG = da[++j3];
        dH = da[++j3];
        dI = da[++j3];
        dJ = da[++j3];
        dK = da[++j3];
        dL = da[++j3];
        dN = da[++j3];
        dM = da[++j3];
        eE = da[++j3];
        dV = da[++j3];
        dr = da[++j3];
        ex = da[++j3];
        for(int j1 = 0; j1 < 4; j1++)
            ds[j1] = da[++j3];

        for(int k1 = 0; k1 < 100; k1++)
            ei[k1] = da[++j3];

        a(100);
        for(int l2 = 0; l2 < cD; l2++)
        {
            for(int i3 = 0; i3 < cD; i3++)
            {
                dY[l2][i3] = db[l2][i3];
                dZ[l2][i3] = dc[l2][i3];
            }

        }

        if(a)
        {
            for(int l1 = 0; l1 < cF; l1++)
            {
                for(int j2 = 0; j2 < 40; j2++)
                    eb[l1][j2] = de[l1][j2];

            }

            for(int i2 = 0; i2 < cG; i2++)
            {
                for(int k2 = 0; k2 < 40; k2++)
                    ea[i2][k2] = dd[i2][k2];

            }

        }
        c(2);
        cR = true;
    }

    public void j(Graphics g1)
    {
        a(g1, true, true, true, false);
        dW = ej / 10;
        be = (ej % 10) * 5;
        dX = ek / 10;
        bf = (ek % 10) * 5;
        ec = 0;
        ct = true;
        cu = true;
        eF = false;
        e(500);
    }

    public void h()
    {
        boolean aflag[][] = new boolean[13][13];
        Random random = new Random();
        if(eb[dY[dP][dO]][3] == 2)
            return;
        if(ea[dZ[dP][dO]][3] == 18)
            return;
        if(cV == 0)
            en++;
        cS = false;
        for(int j1 = 0; j1 < 13; j1++)
        {
            for(int l1 = 0; l1 < 13; l1++)
                aflag[j1][l1] = false;

        }

        for(int k1 = -1; k1 < 12; k1++)
        {
            for(int i2 = -1; i2 < 12; i2++)
            {
                if(aflag[k1 + 1][i2 + 1] || dW * 10 + k1 < 0 || dX * 10 + i2 < 0 || dW * 10 + k1 > cD - 1 || dX * 10 + i2 > cD - 1)
                    continue;
                co = dZ[i2 + dX * 10][k1 + dW * 10];
                if(co == 0 || ea[co][16] <= 0 || ea[co][16] > 3 || ea[co][3] == 18)
                    continue;
                boolean flag = true;
                int j2 = be / 5;
                int k2 = bf / 5;
                if(be % 5 == 1)
                    j2++;
                if(bf % 5 == 1)
                    k2++;
                int l2 = k1;
                if(ea[co][16] == 1)
                {
                    if(k1 > j2)
                        l2 = k1 - 1;
                    else
                    if(k1 < j2)
                        l2 = k1 + 1;
                } else
                if(ea[co][16] == 2)
                    if(k1 > j2)
                        l2 = k1 + 1;
                    else
                    if(k1 < j2)
                        l2 = k1 - 1;
                int i3 = i2;
                if(ea[co][16] == 1)
                {
                    if(i2 > k2)
                        i3 = i2 - 1;
                    else
                    if(i2 < k2)
                        i3 = i2 + 1;
                } else
                if(ea[co][16] == 2)
                    if(i2 > k2)
                        i3 = i2 + 1;
                    else
                    if(i2 < k2)
                        i3 = i2 - 1;
                int j3 = l2;
                int k3 = i3;
                if(!b(l2, i3, j2, k2))
                    if((dQ == '\002' || dQ == '\b') && ea[co][3] == 6 || (dQ == '\004' || dQ == '\006') && ea[co][3] != 6)
                    {
                        if(!b(k1, i3, j2, k2))
                        {
                            if(!b(l2, i2, j2, k2))
                                flag = false;
                            else
                                i3 = i2;
                        } else
                        {
                            l2 = k1;
                        }
                    } else
                    if(!b(l2, i2, j2, k2))
                    {
                        if(!b(k1, i3, j2, k2))
                            flag = false;
                        else
                            l2 = k1;
                    } else
                    {
                        i3 = i2;
                    }
                if(ea[co][16] == 1 || ea[co][16] == 2)
                {
                    byte byte0 = 1;
                    if(ea[co][16] == 1)
                        byte0 = -1;
                    if(j2 != k1 && !flag)
                    {
                        l2 = j3;
                        i3 = i2 + 1 * byte0;
                        if(!b(l2, i3, j2, k2))
                        {
                            i3 = i2 - 1 * byte0;
                            if(!b(l2, i3, j2, k2))
                            {
                                l2 = k1;
                                i3 = i2 + 1 * byte0;
                                if(!b(l2, i3, j2, k2))
                                    i3 = i2 - 1 * byte0;
                            }
                        }
                    }
                    flag = b(l2, i3, j2, k2);
                    if(k2 != i2 && !flag)
                    {
                        i3 = k3;
                        l2 = k1 + 1 * byte0;
                        if(!b(l2, i3, j2, k2))
                        {
                            l2 = k1 - 1 * byte0;
                            if(!b(l2, i3, j2, k2))
                            {
                                i3 = i2;
                                l2 = k1 + 1 * byte0;
                                if(!b(l2, i3, j2, k2))
                                    l2 = k1 - 1 * byte0;
                            }
                        }
                    }
                    flag = b(l2, i3, j2, k2);
                }
                if(!flag)
                {
                    int i1 = 0;
                    do
                    {
                        if(i1 >= 50)
                            break;
                        l2 = k1 + random.nextInt() % 2;
                        i3 = i2 + random.nextInt() % 2;
                        if(b(l2, i3, j2, k2))
                        {
                            flag = true;
                            break;
                        }
                        i1++;
                    } while(true);
                }
                if(!flag)
                    continue;
                if(k1 >= 0 && i2 >= 0 && k1 < 11 && i2 < 11)
                    cs[k1][i2] = true;
                if(l2 >= 0 && i3 >= 0 && l2 < 11 && i3 < 11)
                    cs[l2][i3] = true;
                if(l2 >= -1 && i3 >= -1 && l2 < 12 && i3 < 12)
                    aflag[l2 + 1][i3 + 1] = true;
                dZ[i2 + dX * 10][k1 + dW * 10] = 0;
                dZ[dX * 10 + i3][dW * 10 + l2] = (short)co;
                if(l2 < -1 || i3 < -1 || l2 >= 12 || i3 >= 12)
                    continue;
                if(l2 > k1)
                    cU[i3 + 1][l2 + 1][0] = -5;
                else
                if(l2 < k1)
                    cU[i3 + 1][l2 + 1][0] = 5;
                if(i3 > i2)
                {
                    cU[i3 + 1][l2 + 1][1] = -5;
                    continue;
                }
                if(i3 < i2)
                    cU[i3 + 1][l2 + 1][1] = 5;
            }

        }

    }

    public boolean b(int i1, int j1, int k1, int l1)
    {
        if(dW * 10 + i1 < 0 || dX * 10 + j1 < 0 || dW * 10 + i1 > cD - 1 || dX * 10 + j1 > cD - 1)
            return false;
        return eb[dY[dX * 10 + j1][dW * 10 + i1]][3] != 1 && dZ[dX * 10 + j1][dW * 10 + i1] == 0 && (dY[dX * 10 + j1][dW * 10 + i1] != 0 || dg != 0) && (k1 != i1 || l1 != j1);
    }

    public void k(Graphics g1)
    {
        int i3 = 0;
        int ai1[] = new int[8];
        bA.setColor(Color.white);
        bA.fillRect(0, 0, 440, 440);
        Font font = new Font("Courier", 0, 16);
        bA.setFont(font);
        int k1 = 0;
        do
        {
            if(k1 >= 11)
                break;
            for(int j1 = 0; j1 < 11; j1++)
            {
                short word0 = dZ[k1 + dX * 10][j1 + dW * 10];
                if(ea[word0][3] == 6)
                {
                    int i1;
                    for(i1 = 0; i1 < i3 && ai1[i1] != word0; i1++);
                    if(i1 != i3 && ai1[i1] == word0)
                        continue;
                    cI = ea[word0][10];
                    cJ = ea[word0][11];
                    cK = ea[word0][12];
                    int i2 = cI;
                    int j2 = (ee + cA) - cK;
                    int k2 = cJ - (ef + cB);
                    if(k2 < 0)
                        k2 = 0;
                    int l2 = 0;
                    int l1 = 0;
                    if(j2 > 0)
                        do
                        {
                            l2++;
                            i2 -= j2;
                            if(i2 <= 0)
                                break;
                            l1 += k2;
                        } while(l2 <= 10000);
                    a(bA, "生命力  " + cI, Color.black, Color.gray, 80, 40 + i3 * 50);
                    a(bA, "攻撃力  " + cJ, Color.black, Color.gray, 200, 40 + i3 * 50);
                    a(bA, "防御力  " + cK, Color.black, Color.gray, 80, 60 + i3 * 50);
                    if(j2 > 0)
                        a(bA, "予測ダメージ  " + l1, Color.red, new Color(255, 128, 128), 200, 60 + i3 * 50);
                    else
                        a(bA, "攻撃無効", Color.red, new Color(255, 128, 128), 200, 60 + i3 * 50);
                    bp = ea[word0][1];
                    bA.drawImage(bo[bp], 20, 25 + i3 * 50, this);
                    ai1[i3] = word0;
                    i3++;
                }
                if(i3 >= 8)
                    break;
            }

            if(i3 >= 8)
                break;
            k1++;
        } while(true);
        if(i3 == 0)
        {
            bR = false;
            ch = true;
        } else
        {
            g1.drawImage(bt, 0, 0, this);
        }
    }

    public void c(String s1)
    {
        TextArea textarea = new TextArea();
        TextArea textarea1 = new TextArea("下のボックスのテキストがデータ復帰用のパスワードになっています。\nコピーしてメモ帳などのテキストエディタに貼り付けて保存してください。\n「Ctrl+C」でコピー、「Ctrl+V」で貼り付けできます。\nコピーが効かなかったり、選択状態になっていないときは、\n「Ctrl+Home」でテキストの先頭にカーソルをもってきて、\n「Shift+Ctrl+End」で再度全体を選択してからコピーしてください。\nパスワードは通常、数十行～数百行になります。\n先頭に「Ａ」最後尾に「Ｚ」の文字があることを確認してください。", 8, 100);
        textarea1.setEditable(false);
        eM = new Frame("データ復帰用パスワード");
        eM.setSize(480, 440);
        eM.setLayout(new BorderLayout());
        eM.add("Center", textarea);
        eM.add("North", textarea1);
        eM.add("South", eN);
        eM.show();
        eN.setState(false);
        textarea.setText(s1);
        textarea.selectAll();
        bO = true;
        eP = true;
    }

    public void i()
    {
        TextArea textarea = new TextArea("下のボックスに前回のプレイで取得した復帰用パスワードを入力してください。\nテキストは、Ctrtl+Cでコピー、Ctrl+Vで貼り付けできます。\nテキストの先頭に「Ａ」最後尾に「Ｚ」の文字があることを確認してください。\n作成者がマップの内容を変更した場合は\nそれ以前に取得したパスワードは使えなくなります。", 5, 100);
        textarea.setEditable(false);
        eM = new Frame("データ復帰用パスワード入力");
        eM.setSize(480, 440);
        eM.setLayout(new BorderLayout());
        eM.add("Center", eR);
        eM.add("North", textarea);
        eM.add("South", eO);
        eM.show();
        eO.setState(false);
        eR.setText("");
        bO = true;
        eQ = true;
        eP = true;
    }

    int j()
    {
        int j2 = 0;
        for(int i1 = 0; i1 < 90; i1++)
            if(i1 != 50 && i1 != 51 && i1 != 0 && i1 != 1)
                j2 += a(df[i1]);

        for(int j1 = 0; j1 < cF; j1++)
        {
            for(int l1 = 0; l1 < bc; l1++)
            {
                j2 += eb[j1][l1];
                j2 %= 32000;
            }

        }

        for(int k1 = 0; k1 < cG; k1++)
        {
            for(int i2 = 0; i2 < bd; i2++)
            {
                j2 += ea[k1][i2];
                j2 %= 32000;
            }

        }

        System.out.println("check_data = " + j2);
        return j2;
    }

    void a(int i1, int j1, boolean flag)
    {
        if(flag && j1 > 65000)
            j1 = 65000;
        df[i1] = (byte)j1;
        df[i1 + 1] = (byte)(j1 >> 8);
    }

    void k()
    {
        int j3 = 0;
        byte abyte1[] = new byte[cD * cD * 4];
        if(df == null)
            if(!a)
                df = new byte[cD * cD * 4 + 1000];
            else
                df = new byte[cD * cD * 4 + cF * 80 + cG * 80 + 1000];
        a(54, ed, true);
        a(10, ec, true);
        a(12, ee, true);
        a(14, ef, true);
        a(16, eg, true);
        a(38, dO, false);
        a(40, dP, false);
        a(42, ej, false);
        a(44, ek, false);
        for(int i1 = 0; i1 < 12; i1++)
            a(66 + i1 * 2, eh[i1], false);

        a(46, dU, false);
        a(48, dC, false);
        a(52, ev, false);
        a(56, ew, false);
        a(58, dg, false);
        a(60, en, false);
        a(62, eo, false);
        a(90, dG, false);
        a(92, dH, false);
        a(94, dI, false);
        a(96, dJ, false);
        a(98, dK, false);
        a(100, dL, false);
        a(102, dN, false);
        a(104, dM, false);
        a(106, eE, false);
        a(108, dV, false);
        a(110, dr, false);
        for(int j1 = 0; j1 < 4; j1++)
            a(112 + j1 * 2, ds[j1], false);

        a(120, ex, false);
        for(int k1 = 0; k1 < 100; k1++)
            a(140 + k1 * 2, ei[k1], true);

        j3 = 380;
        for(int l3 = 0; l3 < cD; l3++)
        {
            for(int j4 = 0; j4 < cD; j4++)
            {
                df[j3] = (byte)dY[l3][j4];
                df[j3 + 1] = (byte)(dY[l3][j4] >> 8);
                j3 += 2;
            }

        }

        for(int i4 = 0; i4 < cD; i4++)
        {
            for(int k4 = 0; k4 < cD; k4++)
            {
                df[j3] = (byte)dZ[i4][k4];
                df[j3 + 1] = (byte)(dZ[i4][k4] >> 8);
                j3 += 2;
            }

        }

        if(a)
        {
            for(int l1 = 0; l1 < cF; l1++)
            {
                for(int k2 = 0; k2 < 40; k2++)
                {
                    df[j3] = (byte)eb[l1][k2];
                    df[j3 + 1] = (byte)(eb[l1][k2] >> 8);
                    j3 += 2;
                }

            }

            for(int i2 = 0; i2 < cG; i2++)
            {
                for(int l2 = 0; l2 < 40; l2++)
                {
                    df[j3] = (byte)ea[i2][l2];
                    df[j3 + 1] = (byte)(ea[i2][l2] >> 8);
                    j3 += 2;
                }

            }

        }
        a(50, j(), false);
        int j2 = 2;
        int i5 = 0;
        for(; j2 < j3; j2++)
        {
            i5 += a(df[j2]) * (j2 % 18 + 1);
            i5 %= 32000;
        }

        a(0, i5, false);
        j2 = 0;
        int i3 = 0;
        int k3 = 0;
        for(; j2 < j3; j2++)
        {
            if(df[j2] == df[j2 + 1])
            {
                if(++k3 == 255 || j2 + 2 > j3)
                {
                    abyte1[i3] = df[j2];
                    abyte1[i3 + 1] = df[j2];
                    abyte1[i3 + 2] = (byte)k3;
                    i3 += 3;
                    j2++;
                    k3 = 0;
                }
                continue;
            }
            if(k3 == 0)
            {
                abyte1[i3] = df[j2];
                i3++;
            } else
            {
                abyte1[i3] = df[j2];
                abyte1[i3 + 1] = df[j2];
                abyte1[i3 + 2] = (byte)k3;
                i3 += 3;
            }
            k3 = 0;
        }

        abyte1[i3] = 0;
        abyte1[i3 + 1] = 0;
        abyte1[i3 + 2] = 0;
        int l4 = i3 + 2;
        System.out.println("save_pointer = " + j3 + " " + l4);
        byte abyte0[] = new byte[l4 * 2 + 2000];
        abyte0[0] = 65;
        j2 = 0;
        i3 = 1;
        k3 = 0;
        for(; j2 < l4; j2++)
        {
            abyte0[i3] = (byte)((abyte1[j2] & 0xf) + 66);
            abyte0[i3 + 1] = (byte)((abyte1[j2] >> 4 & 0xf) + 66);
            i3 += 2;
            if(++k3 >= 250)
            {
                abyte0[i3] = 10;
                k3 = 0;
                i3++;
            }
        }

        abyte0[i3] = 90;
        abyte0[i3 + 1] = 10;
        c(new String(abyte0, 0, i3 + 2));
    }

    public void g(int i1)
    {
        dC = i1;
        dD = i1 + 1;
        dE = i1 + 2;
        dF = i1 + 3;
    }

    public int h(int i1)
    {
        return a(df[i1]) + a(df[i1 + 1]) * 256;
    }

    public void l()
    {
        boolean flag1 = false;
        int l4 = 0;
        boolean flag2 = false;
        eQ = false;
        if(df == null)
            if(!a)
                df = new byte[cD * cD * 4 + 1000];
            else
                df = new byte[cD * cD * 4 + cF * 80 + cG * 80 + 1000];
        String s1 = eR.getText();
        byte abyte0[] = s1.getBytes();
        int i6 = s1.length();
        if(i6 == 0)
            return;
        byte abyte1[] = new byte[i6 / 2];
        int i1 = 0;
        int k2 = 0;
        do
        {
            if(k2 >= i6)
                break;
            if(abyte0[k2] == 90)
            {
                flag2 = true;
                break;
            }
            if(!flag1)
            {
                if(abyte0[k2] == 65)
                    flag1 = true;
                if(++k2 > 100)
                    break;
                continue;
            }
            if(abyte0[k2] == 10 || abyte0[k2] == 13)
            {
                k2++;
                continue;
            }
            if(k2 >= i6 - 1)
                break;
            abyte1[i1] = (byte)((abyte0[k2] - 66) + (abyte0[k2 + 1] - 66 << 4));
            i1++;
            k2 += 2;
        } while(true);
        if(!flag2 || !flag1)
        {
            b("パスワードが正常ではありません。\nパスワードテキストの先頭が「Ａ」\n最後尾が「Ｚ」になっているか\n確認してください。", true);
            return;
        }
        i1 = 0;
        k2 = 0;
        boolean flag = false;
        for(; abyte1[i1] != 0 || abyte1[i1 + 1] != 0 || abyte1[i1 + 2] != 0; i1++)
        {
            df[k2] = abyte1[i1];
            k2++;
            if(abyte1[i1] != abyte1[i1 + 1])
                continue;
            int k4 = a(abyte1[i1 + 2]);
            for(int j4 = 0; j4 < k4; j4++)
            {
                df[k2] = abyte1[i1];
                k2++;
            }

            i1 += 2;
        }

        System.out.println("load_pointer = " + k2 + " " + i1);
        l4 = k2 - 1;
        int l5 = h(0);
        i1 = 2;
        int k5 = 0;
        for(; i1 < l4; i1++)
        {
            k5 += a(df[i1]) * (i1 % 18 + 1);
            k5 %= 32000;
        }

        if(k5 != l5)
        {
            b("パスワードが正常ではありません。\nパスワードの一部が欠損していないかなどを確認してください。", true);
            return;
        }
        l5 = h(50);
        if(j() != l5)
        {
            b("このパスワードはこのマップでは\n使用できません。\nマップの内容が前回から変更されているか、または別のマップのパスワードを使用しています。", true);
            return;
        }
        a(99);
        cS = true;
        ed = h(54);
        ec = h(10);
        ee = h(12);
        ef = h(14);
        eg = h(16);
        ej = h(42);
        ek = h(44);
        for(int j1 = 0; j1 < 12; j1++)
            eh[j1] = h(66 + j1 * 2);

        f(0);
        int i5 = h(38);
        int j5 = h(40);
        a(i5, j5);
        dU = h(46);
        c(2);
        g(h(48));
        ev = h(52);
        ew = h(56);
        dg = h(58);
        en = h(60);
        eo = h(62);
        dG = h(90);
        dH = h(92);
        dI = h(94);
        dJ = h(96);
        dK = h(98);
        dL = h(100);
        dN = h(102);
        dM = h(104);
        eE = h(106);
        dV = h(108);
        dr = h(110);
        for(int k1 = 0; k1 < 4; k1++)
            ds[k1] = h(112 + k1 * 2);

        ex = h(120);
        for(int l1 = 0; l1 < 100; l1++)
            ei[l1] = h(140 + l1 * 2);

        a(100);
        l4 = 380;
        for(int j3 = 0; j3 < cD; j3++)
        {
            for(int l3 = 0; l3 < cD; l3++)
            {
                dY[j3][l3] = (short)(a(df[l4]) + a(df[l4 + 1]) * 256);
                l4 += 2;
            }

        }

        for(int k3 = 0; k3 < cD; k3++)
        {
            for(int i4 = 0; i4 < cD; i4++)
            {
                dZ[k3][i4] = (short)(a(df[l4]) + a(df[l4 + 1]) * 256);
                l4 += 2;
            }

        }

        if(a)
        {
            for(int i2 = 0; i2 < cF; i2++)
            {
                for(int l2 = 0; l2 < 40; l2++)
                {
                    eb[i2][l2] = a(df[l4]) + a(df[l4 + 1]) * 256;
                    l4 += 2;
                }

            }

            for(int j2 = 0; j2 < cG; j2++)
            {
                for(int i3 = 0; i3 < 40; i3++)
                {
                    ea[j2][i3] = a(df[l4]) + a(df[l4 + 1]) * 256;
                    l4 += 2;
                }

            }

        }
        cR = true;
    }

    public void a(Graphics g1, String s1, Color color, Color color1, int i1, int j1)
    {
        g1.setColor(color1);
        g1.drawString(s1, i1 + 1, j1);
        g1.setColor(color);
        g1.drawString(s1, i1, j1);
    }

    public static void ReturnExtend(int i1, int j1, int k1, char c1, int l1, int i2, int j2, int k2, 
            int l2, int i3, int j3, int k3, int l3, int i4, int j4, 
            int k4, int l4, boolean flag, boolean flag1, int i5, int j5, int k5, 
            int l5, int i6, int j6, int k6, int l6, int i7, int j7, 
            int k7, int ai1[], int ai2[], short aword0[][], short aword1[][], String s1, String s2, 
            boolean flag2, boolean flag3, int ai3[][], int ai4[][], String as1[], String as2[])
    {
        if(i1 == 4)
        {
            eu = s2;
            return;
        }
        int k8 = ec + ed + ee + ef + eg;
        k8 += dG + dH + dI + dJ + dL + dN + dV;
        int i8 = l1 + i2 + j2 + k2 + l2;
        i8 += k5 + l5 + i6 + j6 + l6 + i7 + k7;
        for(int l7 = 0; l7 < 12; l7++)
        {
            k8 += eh[l7];
            i8 += ai1[l7];
        }

        if(k8 != i8)
            eq = true;
        if(!em)
        {
            int l8 = 0;
            int j8 = 0;
            for(int j9 = 0; j9 < 11; j9++)
            {
                for(int i9 = 0; i9 < 11; i9++)
                {
                    l8 += dY[j9 + dX * 10][i9 + dW * 10] + dZ[j9 + dX * 10][i9 + dW * 10];
                    j8 += aword0[j9 + dX * 10][i9 + dW * 10] + aword1[j9 + dX * 10][i9 + dW * 10];
                }

            }

            l8 += dM;
            j8 += j7;
            if(l8 != j8)
                er = true;
        }
        if(dO != j1 || dP != k1)
            es = true;
        if(eo != j5)
            eD = true;
        if(l1 <= 0 && ec != 0)
            eF = true;
        dO = j1;
        dP = k1;
        dQ = c1;
        ec = l1;
        ed = i2;
        ee = j2;
        ef = k2;
        eg = l2;
        en = i3;
        el = j3;
        ej = k3;
        ek = l3;
        dU = i4;
        dC = j4;
        ev = k4;
        ew = l4;
        em = flag;
        eo = j5;
        dG = k5;
        dH = l5;
        dI = i6;
        dJ = j6;
        dK = k6;
        dL = l6;
        dN = i7;
        dM = j7;
        et = flag1;
        eu = s2;
        ey = flag3;
        dV = k7;
        ex = i5;
        if(flag2)
            er = true;
        if(s1 != null)
        {
            eB = true;
            eC = s1;
            dT = 1;
            dR[dT] = s1;
        }
    }

    public void a(int i1, int j1, int k1, int l1, int i2, int j2, Graphics g1, 
            Graphics g2, Graphics g3, int k2, int l2)
    {
        WWAextendSub wwaextendsub = new WWAextendSub();
        WWAextendSub _tmp = wwaextendsub;
        WWAextendSub.Main(i1, j1, k1, dO, dP, dQ, k2, l2, ec, ed, ee, ef, eg, en, el, ej, ek, dU, dC, ev, ew, em, et, ex, eo, dG, dH, dI, dJ, dK, dL, dN, dM, dV, eh, ei, dY, dZ, l1, i2, j2, cD, cF, cG, cH, g1, g2, g3, bo, eb, ea, dR, dS);
    }

    boolean a;
    final int b = 0;
    final int c = 1;
    final int d = 2;
    final int e = 3;
    final int f = 4;
    final int g = 5;
    final int h = 6;
    final int i = 7;
    final int j = 8;
    final int k = 9;
    final int l = 10;
    final int m = 11;
    final int n = 12;
    final int o = 13;
    final int p = 14;
    final int q = 15;
    final int r = 16;
    final int s = 17;
    final int t = 19;
    final int u = 16;
    final int v = 0;
    final int w = 1;
    final int x = 2;
    final int y = 4;
    final int z = 0;
    final int A = 1;
    final int B = 2;
    final int C = 3;
    final int D = 4;
    final int E = 5;
    final int F = 6;
    final int G = 11;
    final int H = 14;
    final int I = 15;
    final int J = 16;
    final int K = 17;
    final int L = 18;
    final int M = 450;
    final int N = 324;
    final int O = 450;
    final int P = 359;
    final int Q = 449;
    final int R = 394;
    final int S = 0;
    final int T = 1;
    final int U = 2;
    final int V = 3;
    final int W = 4;
    final int X = 5;
    final int Y = 6;
    final int Z = 7;
    final int aa = 8;
    final int ab = 9;
    final int ac = 10;
    final int ad = 11;
    final int ae = 12;
    final int af = 13;
    final int ag = 14;
    final int ah = 446;
    final int ai = 0;
    final int aj = 446;
    final int ak = 35;
    final int al = 446;
    final int am = 70;
    final int an = 446;
    final int ao = 105;
    final int ap = 0;
    final int aq = 2;
    final int ar = 10;
    final int as = 12;
    final int at = 14;
    final int au = 16;
    final int av = 32;
    int aw;
    int ax;
    int ay;
    int az;
    int aA;
    int aB;
    int aC;
    final int aD = 38;
    final int aE = 40;
    final int aF = 42;
    final int aG = 44;
    final int aH = 46;
    final int aI = 48;
    final int aJ = 66;
    final int aK = 46;
    final int aL = 48;
    final int aM = 50;
    final int aN = 52;
    final int aO = 54;
    final int aP = 56;
    final int aQ = 58;
    final int aR = 60;
    final int aS = 62;
    final int aT = 90;
    final int aU = 106;
    final int aV = 108;
    final int aW = 110;
    final int aX = 120;
    final int aY = 140;
    final int aZ = 10000;
    final int ba = 65000;
    final int bb = 100;
    int bc;
    int bd;
    int be;
    int bf;
    int bg;
    String bh;
    String bi;
    String bj;
    int bk;
    String bl;
    MediaTracker bm;
    Image bn;
    Image bo[];
    int bp;
    int bq;
    AudioClip br[];
    boolean bs;
    Image bt;
    Image bu;
    Image bv;
    Image bw;
    Image bx;
    Image by;
    Image bz;
    Graphics bA;
    Graphics bB;
    Graphics bC;
    Graphics bD;
    Graphics bE;
    Graphics bF;
    Graphics bG;
    int bH;
    int bI;
    int bJ;
    int bK;
    boolean bL;
    boolean bM;
    boolean bN;
    boolean bO;
    boolean bP;
    boolean bQ;
    boolean bR;
    boolean bS;
    boolean bT;
    boolean bU;
    boolean bV;
    boolean bW;
    boolean bX;
    boolean bY;
    boolean bZ;
    boolean ca;
    boolean cb;
    boolean cc;
    boolean cd;
    boolean ce;
    boolean cf;
    int cg;
    boolean ch;
    Thread ci;
    int cj;
    int ck;
    int cl;
    int cm;
    int cn;
    int co;
    int cp;
    int cq;
    int cr;
    boolean cs[][];
    boolean ct;
    boolean cu;
    boolean cv;
    int cw;
    boolean cx;
    int cy;
    int cz;
    int cA;
    int cB;
    long cC;
    int cD;
    int cE;
    int cF;
    int cG;
    int cH;
    int cI;
    int cJ;
    int cK;
    int cL;
    int cM;
    int cN;
    boolean cO;
    boolean cP;
    int cQ;
    boolean cR;
    boolean cS;
    boolean cT;
    int cU[][][];
    int cV;
    boolean cW;
    int cX;
    byte cY[];
    byte cZ[];
    int da[];
    short db[][];
    short dc[][];
    int dd[][];
    int de[][];
    byte df[];
    int dg;
    int dh;
    int di;
    int dj;
    int dk;
    int dl;
    int dm;
    boolean dn;
    int _flddo;
    int dp;
    Random dq;
    int dr;
    int ds[];
    int dt;
    int du;
    int dv;
    int dw;
    int dx;
    int dy;
    int dz;
    int dA[];
    boolean dB;
    static int dC = 13;
    static int dD = 14;
    static int dE = 15;
    static int dF = 16;
    static int dG = 23;
    static int dH = 24;
    static int dI = 25;
    static int dJ = 26;
    static int dK = 33;
    static int dL = 34;
    static int dM = 10;
    static int dN = 21;
    static int dO;
    static int dP;
    static char dQ = '\002';
    static String dR[];
    static String dS[] = new String[20];
    static int dT;
    static int dU = 2;
    static int dV = 0;
    static int dW;
    static int dX;
    static short dY[][];
    static short dZ[][];
    static int ea[][];
    static int eb[][];
    static int ec;
    static int ed;
    static int ee;
    static int ef;
    static int eg;
    static int eh[] = new int[12];
    static int ei[] = new int[100];
    static int ej;
    static int ek;
    static int el = 20;
    static boolean em = true;
    static int en = 0;
    static int eo = 0;
    static boolean ep;
    static boolean eq;
    static boolean er;
    static boolean es = false;
    static boolean et = false;
    static String eu;
    static int ev = 0;
    static int ew = 0;
    static int ex = 1;
    static boolean ey = false;
    static int ez;
    static int eA;
    static boolean eB = false;
    static String eC;
    static boolean eD = false;
    static int eE = 0;
    static boolean eF = false;
    String eG;
    int eH;
    int eI;
    int eJ;
    int eK;
    String eL;
    Frame eM;
    Checkbox eN;
    Checkbox eO;
    boolean eP;
    boolean eQ;
    TextArea eR;

}
