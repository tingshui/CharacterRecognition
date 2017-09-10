clc;
% Read in files
trans = 'C:\Users\QUANQING\Desktop\result\transi.csv';
emiss = 'C:\Users\QUANQING\Desktop\result\emiss.csv';
predSeqf = 'C:\Users\QUANQING\Desktop\result\predSeq_mod.csv';
oriSeqf = 'C:\Users\QUANQING\Desktop\result\originSeq_mod.csv';
[transRead] = csvread(trans);
[emissRead] = csvread(emiss);
[transD] = transRead(:,1:end-1);
[emissD] = emissRead(:,1:end-1);
[predSeRead] = csvRead(predSeqf);
[oriSeRead] = csvRead(oriSeqf);
[rp, cp] = size(predSeRead);
[ro, co] = size(oriSeRead);
% perform calculation
count_right = 0;
count_all = 0;
count_right_hmm = 0;
for n = 1: rp
    rowP = predSeRead(n,:);
    rowP(rowP == 0) = [];
    likestate = hmmviterbi(rowP, transD, emissD);
     rowo = oriSeRead(n,:);
    rowo(rowo == 0) = [];
    [rowpR,rowpC] = size(rowP);
    [rowoR,rowoC] = size(rowo);
    for m = 1: rowpC
        if rowP(1,m) == rowo(1,m)
            count_right = count_right + 1;
        end
        if likestate(1,m) == rowo(1,m) 
            count_right_hmm = count_right_hmm + 1;
        end
        count_all = count_all + 1;
    end
end
disp('right predict number:');
disp(count_right);
disp('right hmm predict number: ');
disp(count_right_hmm);
disp('total predict number:');
disp(count_all);
disp('original accuracy:');
disp(count_right/count_all*100);
disp('hmm accuracy:');
disp(count_right_hmm/count_all*100);




